package com.abishake.fitnesstracker

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container


@TestConfiguration
class TestContainerConfiguration {

    companion object {
        @Container
        val postgres = PostgreSQLContainer<Nothing>("postgres:17").apply {
            withDatabaseName("test_db")
            withUsername("test_user")
            withPassword("test_password")
            start()
        }

        @DynamicPropertySource
        fun overrideProps(registry: DynamicPropertyRegistry) {
            registry.apply {
                add("spring.datasource.url", postgres::getJdbcUrl)
                add("spring.datasource.username", postgres::getUsername)
                add("spring.datasource.password", postgres::getPassword)
            }
        }
    }
}