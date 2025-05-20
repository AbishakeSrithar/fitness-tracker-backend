package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Long> {
    fun findByName(name: String): List<Exercise>
    fun findByNameAndDescription(name: String, description: String): List<Exercise>
}