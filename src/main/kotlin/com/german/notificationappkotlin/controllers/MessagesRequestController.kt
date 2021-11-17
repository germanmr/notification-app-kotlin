package com.german.notificationappkotlin.controllers

import com.german.notificationappkotlin.service.DispatcherService
import com.german.notificationappkotlin.service.MessageRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("messages")
class MessagesRequestController {

    @Autowired
    @Qualifier("emailDispatcherServiceImpl")
    lateinit var emailDispatcherServiceImpl: DispatcherService

    @Autowired
    lateinit var messageRequestService: MessageRequestService

    @PutMapping("/dispatch")
    fun dispatch() {
        emailDispatcherServiceImpl.dispatch(1)
    }

    @DeleteMapping("/{id}")
    fun deleteRequestMessage(@PathVariable("id") messageRequestId: Long): ResponseEntity<String> {

        return try {
            messageRequestService.deleteMessageRequest(messageRequestId)

            ResponseEntity.status(HttpStatus.OK).build()
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/cancel/{id}")
    fun cancelMessageRequest(@PathVariable("id") messageRequestId: Long): ResponseEntity<Any> {
        val cancelMessageRequest = messageRequestService.cancelMessageRequest(messageRequestId)

        // If we want 404 when not found we must use an if
        // We use this
        return if (cancelMessageRequest == null)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(cancelMessageRequest)
    }

    @GetMapping("/ping")
    fun ping(): String {
        return "hello"
    }
}