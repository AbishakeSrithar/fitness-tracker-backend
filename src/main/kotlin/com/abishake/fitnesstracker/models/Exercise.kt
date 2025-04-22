package com.abishake.fitnesstracker.models

import jakarta.persistence.*

@Entity
@Table(name = "exercises")
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String
)