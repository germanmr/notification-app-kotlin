package com.german.notificationappkotlin.domain

import javax.persistence.*

// TODO many to one - one to many!
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class Client(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
    val name: String,
    val favoriteMedia: Medias,
    val favoriteMediaIdentifier: String
)