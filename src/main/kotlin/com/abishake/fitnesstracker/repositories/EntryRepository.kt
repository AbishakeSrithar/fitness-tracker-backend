package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EntryRepository : JpaRepository<Entry, Int> {
    fun findByWorkoutId(workoutId: Int): List<Entry>
    fun findByExerciseId(exerciseId: Int): List<Entry>
}