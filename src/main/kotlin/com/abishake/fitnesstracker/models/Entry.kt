package com.abishake.fitnesstracker.models

import jakarta.persistence.*

@Entity
@Table(name = "entries")
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    val workoutId: Int,

    val exerciseId: Int,

    val weight: Double,

    val sets: Int,

    val reps: Int
)