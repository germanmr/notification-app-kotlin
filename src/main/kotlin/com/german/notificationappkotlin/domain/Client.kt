package com.german.notificationappkotlin.domain

import javax.persistence.Embeddable

// TODO many to one - one to many!
@Embeddable
data class Client(
    val name: String,
    val favoriteMedias: Medias,
    val favoriteMediaIdentifier: Medias
)