package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Entry
import org.springframework.data.jpa.repository.JpaRepository

interface EntryRepository : JpaRepository<Entry, Int> {
    fun findByWorkoutId(workoutId: Int): List<Entry>
    fun findByExerciseId(exerciseId: Int): List<Entry>
}