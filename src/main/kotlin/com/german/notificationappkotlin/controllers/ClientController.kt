package com.german.notificationappkotlin.controllers

import com.german.notificationappkotlin.controllers.dto.ComplexClientDTO
import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.ComplexClient
import com.german.notificationappkotlin.exceptions.ClientNotFoundException
import com.german.notificationappkotlin.external.RestTemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("clients")
class ClientController {

    @Autowired
    lateinit var restTemplateService: RestTemplateService

    @GetMapping("/external/{clientID}")
    fun getClientFromExternalSource(@PathVariable("clientID") clientID: Long): ComplexClientDTO {
        // First we have to find the client
        val obtainedComplexClientDTO = restTemplateService.getComplexClient().let { it.body }

        obtainedComplexClientDTO ?: throw ClientNotFoundException()

        println("obtainedComplexClientDTO : $obtainedComplexClientDTO")
        val complexClient = obtainedComplexClientDTO.toEntity()

        println("complexClient : $complexClient")
        val complexClientDTO = complexClient.toDTO()

        println("Returning : $complexClientDTO")
        return complexClientDTO
    }
}

fun ComplexClientDTO.toEntity(): ComplexClient {
    return ComplexClient(description, Client(name, favoriteMedia, favoriteMediaIdentifier))
}

fun ComplexClient.toDTO(): ComplexClientDTO {
    return ComplexClientDTO(description, client.name, client.favoriteMedia, client.favoriteMediaIdentifier)
}