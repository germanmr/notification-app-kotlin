package com.german.notificationappkotlin.controllers.dto

import com.german.notificationappkotlin.domain.Medias

class ComplexClientDTO(
    val description: String,
    name: String,
    favoriteMedia: Medias,
    favoriteMediaIdentifier: String
) : ClientDTO(
    name,
    favoriteMedia,
    favoriteMediaIdentifier
)