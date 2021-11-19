package com.german.notificationappkotlin.domain

import com.german.notificationappkotlin.controllers.dto.ClientDTO
import javax.persistence.Embeddable

// TODO many to one - one to many!
@Embeddable
data class Client(
    val name: String,
    val favoriteMedia: Medias,
    val favoriteMediaIdentifier: String
)