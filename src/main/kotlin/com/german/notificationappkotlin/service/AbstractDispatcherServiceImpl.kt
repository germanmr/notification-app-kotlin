package com.german.notificationappkotlin.service

import com.german.notificationappkotlin.repositories.MessageRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger
import javax.transaction.Transactional

@Service
class AbstractDispatcherServiceImpl : DispatcherService {

    companion object {
        val LOG: Logger = Logger.getLogger(AbstractDispatcherServiceImpl::class.java.name)
    }

    @Autowired
    lateinit var messageRequestRepository: MessageRequestRepository

    @Transactional
    override fun dispatch(batchSize: Long) {
        LOG.info("Starting dispatch")

//            Long actualBatchsize = batchSize > MAX_BATCH_SIZE ? MAX_BATCH_SIZE : batchSize;
//            logger.info("Begin batch publish - batch size: {}", actualBatchsize);
        val actualBatchSize = 10L
        messageRequestRepository.getBatchForUpdateById(actualBatchSize)
            .forEach {

//                try {
//                LOG.info("Begin Publish message: " + messageRequest.getId());
                it.setRequestBeginProcessing()
                messageRequestRepository.save(it)
                LOG.info("#### -> Dispatching message -> $it")
                LOG.info("Before Kafka exception");
//                String message = objectMapper . writeValueAsString (messageRequest);
//                this.kafkaTemplate.send(topic, message);
//                logger.info("End Publish message: ", messageRequest.getId());
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