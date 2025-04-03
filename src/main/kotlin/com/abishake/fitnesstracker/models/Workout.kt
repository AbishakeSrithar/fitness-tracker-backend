package com.abishake.fitnesstracker.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "workouts")
data class Workout(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)