package com.abishake.fitnesstracker.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "workouts")
data class Workout(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var date: LocalDate = LocalDate.now()
)