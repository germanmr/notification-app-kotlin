package com.german.notificationappkotlin.service.validating

import com.german.notificationappkotlin.domain.Medias

abstract class ClientDTO(
    val name: String,
    val favoriteMedia: Medias,
    val favoriteMediaIdentifier: String
)