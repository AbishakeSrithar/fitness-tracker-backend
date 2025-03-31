package com.abishake.fitnesstracker.fitness_tracker.repositories

import com.abishake.fitnesstracker.fitness_tracker.models.Workout
import org.springframework.data.jpa.repository.JpaRepository

interface WorkoutRepository : JpaRepository<Workout, Int>