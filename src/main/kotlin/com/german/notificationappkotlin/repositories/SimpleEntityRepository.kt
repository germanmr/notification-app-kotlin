package com.german.notificationappkotlin.repositories

//import com.german.notificationappkotlin.domain.Demo
import com.german.notificationappkotlin.domain.SimpleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SimpleEntityRepository : JpaRepository<SimpleEntity, Long>

//interface DemoRepository : JpaRepository<Demo, Long>