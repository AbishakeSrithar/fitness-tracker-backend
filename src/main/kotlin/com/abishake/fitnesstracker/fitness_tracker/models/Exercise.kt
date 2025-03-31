package com.abishake.fitnesstracker.fitness_tracker.models

import jakarta.persistence.*

@Entity
@Table(name = "Exercises")
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @Column(name = "desc")
    val description: String
)