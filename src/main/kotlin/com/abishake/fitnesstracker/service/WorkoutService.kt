package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {
    // CREATE
    fun createWorkout(name: String, createdAt: LocalDate = LocalDate.now()): Workout {
        return workoutRepository.save(
            Workout(null, name, createdAt)
        )
    }


    // READ
    fun getAllWorkouts(): List<Workout> {
        return workoutRepository.findAll()
    }

    fun getWorkoutById(id: Int): Optional<Workout> {
        return workoutRepository.findById(id)
    }

    fun getWorkoutByName(name: String): List<Workout> {
        return workoutRepository.findByName(name)
    }

    fun getWorkoutByDate(date: LocalDate): List<Workout> {
        return workoutRepository.findByCreatedAt(date)
    }
}