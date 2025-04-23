package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExerciseRepository : JpaRepository<Exercise, Long> {
    fun findByName(name: String): Optional<Exercise>
}