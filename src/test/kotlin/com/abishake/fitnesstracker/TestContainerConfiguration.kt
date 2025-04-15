package com.abishake.fitnesstracker

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer


@TestConfiguration
class TestContainerConfiguration {

    @Bean
    fun postgreSQLContainer(): PostgreSQLContainer<*> {
        val postgres = PostgreSQLContainer<Nothing>("postgres:17")
            .apply {
                withDatabaseName("test_db")
                withUsername("test_user")
                withPassword("test_password")
            }
        postgres.start() // Start the container
        return postgres
    }
}