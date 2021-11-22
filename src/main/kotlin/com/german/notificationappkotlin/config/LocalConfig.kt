package com.german.notificationappkotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("local")
class LocalConfig {

//    @Bean(name = ["datasource"])
//    @Primary
//    fun dataSource(): DataSource? {
//        return DriverManagerDataSource().apply {
//            this.setDriverClassName("org.postgresql.Driver")
//            this.password = "postgres"
//            this.username = "postgres"
//            this.url = "jdbc:postgresql://localhost:5432/messages"
//        }
//    }
}