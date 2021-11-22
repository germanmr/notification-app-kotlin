package com.german.notificationappkotlin.controllers.dto.map

import com.german.notificationappkotlin.domain.Client

fun Client.introduceYourself() = println("Hello I'm the client $this.name")