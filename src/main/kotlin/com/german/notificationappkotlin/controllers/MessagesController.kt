package com.german.notificationappkotlin.controllers

import com.german.notificationappkotlin.service.DispatcherService
import com.german.notificationappkotlin.service.dataloading.DataLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagesController {

    @Autowired
//    lateinit var dispatcherServiceImpl: AbstractDispatcherServiceImpl
//    @Qualifier("EmailDispatcherServiceImplImpl")
//    lateinit var dispatcherServiceImpl: AbstractDispatcherServiceImpl

//    @Autowired
    @Qualifier("emailDispatcherServiceImpl")
    lateinit var emailDispatcherServiceImpl: DispatcherService

    @Autowired
    lateinit var dataLoader: DataLoader

    @PutMapping("/dispatch")
    fun dispatch() {
        emailDispatcherServiceImpl.dispatch(1)
    }

    @PutMapping("/loaddata")
    fun loadData() {
        dataLoader.loadData()
    }

    @GetMapping("/ping")
    fun ping(): String {
        return "hello"
    }

}