package com.abishake.fitnesstracker.fitness_tracker.models

import jakarta.persistence.*

@Entity
@Table(name = "Entries")
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

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