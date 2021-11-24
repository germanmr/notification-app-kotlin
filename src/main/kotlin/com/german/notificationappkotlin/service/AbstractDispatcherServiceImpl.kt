package com.german.notificationappkotlin.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.german.notificationappkotlin.controllers.dto.MessageRequestDTO
import com.german.notificationappkotlin.controllers.dto.map.toDTO
import com.german.notificationappkotlin.domain.MessageRequest
import com.german.notificationappkotlin.log.Utils
import com.german.notificationappkotlin.repositories.MessageRequestRepository
import com.german.notificationappkotlin.service.publisher.KotlinProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class AbstractDispatcherServiceImpl : DispatcherService {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = Utils.getLogger(javaClass.enclosingClass)
    }

    @Autowired
    lateinit var messageRequestRepository: MessageRequestRepository

    private val mapper by lazy { ObjectMapper() }

    @Autowired
    lateinit var kotlinProducer: KotlinProducer


    // TODO Add exception handling!!
    @Transactional
    override fun dispatch(batchSize: Long) {
        logger.info("Starting dispatch")

//            Long actualBatchsize = batchSize > MAX_BATCH_SIZE ? MAX_BATCH_SIZE : batchSize;
//            logger.info("Begin batch publish - batch size: {}", actualBatchsize);
        val actualBatchSize = 10L
        messageRequestRepository.getBatchForUpdateById(actualBatchSize)
            .forEach {
//                try {
//                logger.info("Begin Publish message: " + messageRequest.getId());
                it.setRequestBeginProcessing()
                messageRequestRepository.save(it)
                logger.info("#### -> Dispatching message -> $it")
                logger.info("Before Kafka exception")
                // Create MessageRequestDTO?
                val messageRequestDTO = it.toDTO()
                val message = mapper.writeValueAsString(messageRequestDTO)
                kotlinProducer.send(message)
                logger.info("End Publish message: ${it.id}")
//            } catch (Exception e) {
//                // If we got an exception the ones that have been sent are still committed and marked as PROCESSED
//                // The error one should be back to its state
//                messageRequest.setBeginProcessing();
//                // Si tenemos un error aqui el mensaje ya fue
//                // enviado pero volvemos la transaccion para atras?
//                messageRequestRepository.save(messageRequest);
//                logger.error(String.format("#### -> There was an error sending the message -> %s", e.getMessage()));
//            }
//            }
//        logger.info("End of batch publish - batch size: " + actualBatchsize);
            }
    }
}

fun MessageRequest.toDTO(): MessageRequestDTO = MessageRequestDTO(
    uuid = uuid,
    client = client.toDTO(),
    publication = publication.toDTO(),
    error = error,
    messageState = messageState.toString()
)