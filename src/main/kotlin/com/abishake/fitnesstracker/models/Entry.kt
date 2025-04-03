package com.abishake.fitnesstracker.models

import jakarta.persistence.*

@Entity
@Table(name = "entries")
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    val workout: Workout,

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    val exercise: Exercise,

    val weight: Double,
    val sets: Int,
    val reps: Int
)