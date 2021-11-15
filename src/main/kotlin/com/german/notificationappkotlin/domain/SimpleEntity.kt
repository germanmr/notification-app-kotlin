package com.german.notificationappkotlin.domain

import javax.persistence.*

@Entity
@Table(name = "simple_entity")
data class SimpleEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
    val name: String
)