package com.german.notificationappkotlin.service

import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.MessageRequest
import com.german.notificationappkotlin.domain.MessageStates
import com.german.notificationappkotlin.domain.Publication
import com.german.notificationappkotlin.exceptions.MessageRequestNotFoundException
import com.german.notificationappkotlin.repositories.ClientRepository
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
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var messageRequestRepository: MessageRequestRepository

    @Transactional
    fun saveData(clients: List<Client>, publication: Publication) {
        logger.info("Start - Saving Data")
        clients.forEach {
            logger.info("Saving client: $it")
            val client = clientRepository.save(it)

            logger.info("Creating message request with saved client: $client")
            val messageRequest = MessageRequest(id = 0, client = client, publication = publication)
            logger.info("Message request created: $messageRequest")
            messageRequestRepository.save(messageRequest)
        }
        logger.info("End - Saving Data")
    }

    /**
     * Example of method that does return value and fires Exceptions!
     * We use Exceptions and the Error handler
     * 1) Entity not FOUND              ->  Fire Exception
     * 2) Entity not PENDING state      ->  Fire Exception
     * 3) Correct deletion              ->  Do not return any value
     */
    fun deleteMessageRequest(messageRequestId: Long) {

        val foundMessageRequest = messageRequestRepository.findByIdOrNull(messageRequestId)

        foundMessageRequest
            ?: throw MessageRequestNotFoundException("The request with ID: $messageRequestId is nonexistent")

        check(foundMessageRequest.messageState == MessageStates.PENDING) { "Error on request id: $messageRequestId, only request in State PENDING can be deleted" }

        messageRequestRepository.delete(foundMessageRequest)
    }

    // Returns a result and does NOT fire any Exceptions
    //                          Easy approach , only two results, can or can´t cancel
    /**
     * This method cancels the entity or returns null if the message request was not found
     * Take into account this comment when consuming it!
     */
    fun cancelMessageRequest(messageRequestId: Long): MessageRequest? {

        // We should not use this because we have the method
        val foundMessageRequest = messageRequestRepository.findByIdOrNull(messageRequestId)

        // We have to use this
        foundMessageRequest
            ?.apply {
                foundMessageRequest.messageState = MessageStates.CANCELLED
                messageRequestRepository.save<MessageRequest>(foundMessageRequest)
            }
        return foundMessageRequest
    }


//    fun deleteMessageRequest(messageRequestId: Long) {
//
//        val foundMessageRequest = messageRequestRepository.findByIdOrNull(messageRequestId)
////        val foundMessageRequest = messageRequestRepository
////            .findById(messageRequestId)
////            .orElseThrow { MessageRequestNotFoundException("The request with ID: $messageRequestId is nonexistent") }
//
//        // check(foundMessageRequest != null) { "The request with ID: $messageRequestId was already deleted" }
//        // foundMessageRequest ?: throw IllegalStateException("The request with ID: $messageRequestId was already deleted")
//
//        // We fire an exception to be caught by the handler
//        // check() fires an Illegal State Exception
//        // check(foundMessageRequest.messageState == null) { "The request with ID: $messageRequestId is nonexistent" }
//        foundMessageRequest
//            ?: throw MessageRequestNotFoundException("The request with ID: $messageRequestId is nonexistent")
//
//        check(foundMessageRequest.messageState == MessageStates.PENDING) { "Error on request id: $messageRequestId, only request in State PENDING can be deleted" }
//
//        messageRequestRepository.delete(foundMessageRequest)
//
//        // This is idiomatic but, it is not as clear as first checking the state separately and then deleting the entity
////        when (foundMessageRequest.messageState) {
////            MessageStates.PENDING ->
////                // HERE Kotlin compiler smart casts foundMessageRequest nullable to foundMessageRequest NON nullable
////                messageRequestRepository.delete(foundMessageRequest)
////            else ->
//////                throw IllegalStateException("Only request in State PENDING can be deleted")
////                throw IllegalStateException("Error on request id: $messageRequestId, only request in State PENDING can be deleted")
////        }
//    }

//    fun deleteMessageRequest(messageRequestId: Long) {
//
//        require(messageRequestId != null) { "The message request ID cannot be null" }
//
//        messageRequestRepository.findByIdOrNull(messageRequestId)
//            // This take if only returns MessageRequest if: ( it != null )
//            ?.takeIf {
//                it != null
//                // This let only performs the deletion if the previous takeIf was NOT null
//            }?.let {
//                messageRequestRepository.delete(it)
//            }
//    }

//    fun cancelMessageRequest(messageRequestId: Long): MessageRequest? {
//
//        val foundEntity = messageRequestRepository.findByIdOrNull(messageRequestId)
//
//        if (foundEntity != null) messageRequestRepository.save(foundEntity)
//
//        return foundEntity
//    }


}