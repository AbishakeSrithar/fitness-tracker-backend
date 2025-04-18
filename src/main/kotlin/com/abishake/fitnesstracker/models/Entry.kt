package com.abishake.fitnesstracker.models

import jakarta.persistence.*

@Entity
@Table(name = "entries")
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @ManyToOne
    @JoinColumn(nullable = false)
    val workoutId: Int,

    @ManyToOne
    @JoinColumn(nullable = false)
    val exerciseId: Int,

    val weight: Double,
    val sets: Int,
    val reps: Int
)