package com.german.notificationappkotlin.controllers.dto.map

import com.german.notificationappkotlin.controllers.dto.ClientDTO
import com.german.notificationappkotlin.controllers.dto.ComplexClientDTO
import com.german.notificationappkotlin.controllers.dto.PublicationDTO
import com.german.notificationappkotlin.controllers.dto.SimpleClientDTO
import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.ComplexClient
import com.german.notificationappkotlin.domain.Publication
import com.german.notificationappkotlin.domain.SimpleClient


fun ComplexClientDTO.toEntity(): ComplexClient {
    return ComplexClient(description, id, name, favoriteMedia, favoriteMediaIdentifier)
}

fun ComplexClient.toDTO(): ComplexClientDTO {
    return ComplexClientDTO(description, id, name, favoriteMedia, favoriteMediaIdentifier)
}

fun SimpleClient.toDTO(): ClientDTO {
    return SimpleClientDTO(id, name, favoriteMedia, favoriteMediaIdentifier)
}

fun Client.toDTO(): ClientDTO {
    return when (this) {
        is SimpleClient -> toDTO()
        is ComplexClient -> toDTO()
        else -> throw IllegalStateException("This is not a valid subclass")
    }
}

fun Publication.toDTO(): PublicationDTO {
    return PublicationDTO(message)
}