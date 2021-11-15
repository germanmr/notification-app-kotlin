package com.german.notificationappkotlin.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagesController {

    @GetMapping("/ping")
    fun ping(): String {
        return "hello"
    }

}