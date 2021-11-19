package com.german.notificationappkotlin.controllers.dto

import com.german.notificationappkotlin.domain.Medias

abstract class ClientDTO(
    val name: String,
    val favoriteMedia: Medias,
    val favoriteMediaIdentifier: String
)
