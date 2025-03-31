package com.abishake.fitnesstracker.fitness_tracker.repositories

import com.abishake.fitnesstracker.fitness_tracker.models.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Int>