package com.german.notificationappkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NotificationAppKotlinApplication

fun main(args: Array<String>) {

//    val dbUrl = "jdbc:postgresql://localhost:5432/messages"
//    val dbUser = "postgres"
//    val dbPass = "postgres"
//    Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)
//    transaction {
//        addLogger(StdOutSqlLogger)
//        with(SchemaUtils) {
//            drop(Demo)
//            create(Demo)
////            drop(MessageRequest)
////            create(MessageRequest)
//        }
//        Demo.insert {
//            it[name] = "Teste 1"
//        }
//        Demo.insert {
//            it[name] = "Teste 2"
//        }
//    }

    runApplication<NotificationAppKotlinApplication>(*args)
}