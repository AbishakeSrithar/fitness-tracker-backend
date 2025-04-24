package com.abishake.fitnesstracker.models

import jakarta.persistence.*

@Entity
@Table(name = "entries")
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val workoutId: Int,
    val exerciseId: Int,
    var weight: Double,
    var sets: Int,
    var reps: Int
)