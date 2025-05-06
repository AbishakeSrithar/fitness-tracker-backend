package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {

    private val className = WorkoutService::class

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

    fun getWorkoutById(id: Long): Workout {
        if (workoutRepository.findById(id).isPresent) {
            return workoutRepository.findById(id).get()
        } else {
            throw Exception("Workout Id = $id not found for getWorkoutById() >> $className")
        }
    }

    fun getWorkoutsByName(name: String): List<Workout> {
        return workoutRepository.findByName(name)
    }

    fun getWorkoutsByDate(date: LocalDate): List<Workout> {
        return workoutRepository.findByDate(date)
    }

    //  UPDATE
    fun updateWorkoutById(id: Long, name: String, date: LocalDate): Workout {
        try {
            val workout = getWorkoutById(id)
            workout.name = name
            workout.date = date

            workoutRepository.saveAndFlush(workout)
            return workout
        } catch (e: Exception) {
            throw Exception("Exception in updateWorkoutById() >> $className", e)
        }
    }

    // DELETE
    fun deleteWorkoutById(id: Long): Workout {
        val workout = getWorkoutById(id)
        try {
            workoutRepository.deleteById(id)
            return workout
        } catch (e: Exception) {
            throw Exception("Exception in deleteWorkoutById() >> $className", e)
        }
    }
}