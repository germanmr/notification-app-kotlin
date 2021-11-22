package com.german.notificationappkotlin.controllers.dto.map

import com.german.notificationappkotlin.controllers.dto.ComplexClientDTO
import com.german.notificationappkotlin.domain.Client
import com.german.notificationappkotlin.domain.ComplexClient


fun ComplexClientDTO.toEntity(): ComplexClient {
    return ComplexClient(description, Client(name, favoriteMedia, favoriteMediaIdentifier))
}

fun ComplexClient.toDTO(): ComplexClientDTO {
    return ComplexClientDTO(description, client.name, client.favoriteMedia, client.favoriteMediaIdentifier)
}