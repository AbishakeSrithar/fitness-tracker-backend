package com.abishake.fitnesstracker.fitness_tracker.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Workouts")
data class Workout(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)