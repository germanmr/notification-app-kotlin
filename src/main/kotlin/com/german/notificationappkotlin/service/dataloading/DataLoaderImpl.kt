package com.german.notificationappkotlin.service.dataloading

import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.Medias
import com.german.notificationappkotlin.domain.Publication
import com.german.notificationappkotlin.log.Utils.Companion.getLogger
import com.german.notificationappkotlin.service.MessageRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DataLoaderImpl : DataLoader {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = getLogger(javaClass.enclosingClass)
    }

    @Autowired
    lateinit var messageRequestService: MessageRequestService

    override fun loadData() {
        val clients = arrayListOf(
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("German", Medias.MAIL, "someemail@gmail.com"),
            Client("Peter", Medias.MAIL, "peteremail@gmail.com")
        )
        val publication = Publication("This is a the message to be send!!")

        messageRequestService.saveData(clients, publication)
    }
}