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
    fun createWorkout(name: String, date: LocalDate = LocalDate.now()): Workout {
        return workoutRepository.saveAndFlush(
            Workout(null, name, date)
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
        return workoutRepository.findByDate(date)
    }

    //  UPDATE
    fun updateWorkoutById(id: Long, name: String, date: LocalDate): RestResponse {
        if (getWorkoutById(id).isPresent) {
            val workout = getWorkoutById(id).get()
            workout.name = name
            workout.date = date

            try {
                workoutRepository.saveAndFlush(workout)
                return RestResponse("True", "Successfully updated Workout with ID: $id to have name=$name, date=$date")
            } catch (e: Exception) {
                return RestResponse("False", "Error while updating Workout with ID: $id")
            }
        } else {
            return RestResponse("False", "Cannot find Workout with ID: $id to update")
        }
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