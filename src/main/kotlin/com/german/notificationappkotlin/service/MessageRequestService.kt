package com.german.notificationappkotlin.service

import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.MessageRequest
import com.german.notificationappkotlin.domain.Publication
import com.german.notificationappkotlin.repositories.MessageRequestRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MessageRequestService {
    private val logger = LoggerFactory.getLogger(MessageRequestService::class.java)

    @Autowired
    lateinit var messageRequestRepository: MessageRequestRepository

    @Transactional
    fun saveData(clients: List<Client>, publication: Publication) {

        logger.info("Start - Saving Data")
        clients.forEach {
            logger.info("Creating message request with client: $it")
            val messageRequest =
                MessageRequest(id = 0, client = it, publication = Publication("This is a test message"))
            logger.info("Message request created: $messageRequest")
            messageRequestRepository.save(messageRequest)
        }
        logger.info("End - Saving Data")
    }

}