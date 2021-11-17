package com.german.notificationappkotlin.service

import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.MessageRequest
import com.german.notificationappkotlin.domain.MessageStates
import com.german.notificationappkotlin.domain.Publication
import com.german.notificationappkotlin.repositories.MessageRequestRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class MessageRequestService {
    private val logger = LoggerFactory.getLogger(MessageRequestService::class.java)

    @Autowired
    lateinit var messageRequestRepository: MessageRequestRepository

    @Transactional
    fun saveData(clients: List<Client>, publication: Publication) {
        logger.info("Start - Saving Data")
        clients.forEach {
            logger.info("Creating message request with client: $it")
            val messageRequest = MessageRequest(id = 0, client = it, publication = publication)
            logger.info("Message request created: $messageRequest")
            messageRequestRepository.save(messageRequest)
        }
        logger.info("End - Saving Data")
    }

    // Three different behaviour for this method:
    // 1) Does not find the requested entity: return false
    // 2) There is a problem when deleting, it will fire an Exception
    // 3) It deletes the entity correctly and returns true
    // When consuming the method we must handle true or false to give the success and failure case
    /**
     * We can return aen Exception in this case
     */
    fun deleteMessageRequest(messageRequestId: Long) {

        require(messageRequestId != null)

        val foundMessageRequest = messageRequestRepository.findByIdOrNull(messageRequestId)

        require(foundMessageRequest != null)

        messageRequestRepository.delete(foundMessageRequest)
    }

//    fun cancelMessageRequest(messageRequestId: Long): MessageRequest? {
//
//        val foundEntity = messageRequestRepository.findByIdOrNull(messageRequestId)
//
//        if (foundEntity != null) messageRequestRepository.save(foundEntity)
//
//        return foundEntity
//    }

    fun cancelMessageRequest(messageRequestId: Long): MessageRequest? {

        val foundEntity = messageRequestRepository.findByIdOrNull(messageRequestId)
            ?.apply {
                messageState = MessageStates.CANCELLED
                messageRequestRepository.save(this)
            }

        return foundEntity
    }
}