package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {
    // CREATE
    fun createWorkout(name: String, createdAt: LocalDate = LocalDate.now()): Workout {
        return workoutRepository.saveAndFlush(
            Workout(null, name, createdAt)
        )
    }

    // READ
    fun getAllWorkouts(): List<Workout> {
        return workoutRepository.findAll()
    }

    fun getWorkoutById(id: Long): Optional<Workout> {
        return workoutRepository.findById(id)
    }

    fun getWorkoutByName(name: String): List<Workout> {
        return workoutRepository.findByName(name)
    }

    fun getWorkoutByDate(date: LocalDate): List<Workout> {
        return workoutRepository.findByCreatedAt(date)
    }

    // DELETE
    fun deleteWorkoutById(id: Long): RestResponse {
        if (workoutRepository.findById(id).isPresent) {
            workoutRepository.deleteById(id)
            return RestResponse("True", "Successfully deleted Workout with ID: $id")
        } else {
            return RestResponse("False", "Workout ID: $id not found")
        }
    }
}