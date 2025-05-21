package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {

    // CREATE
    fun createWorkout(name: String, date: LocalDate = LocalDate.now()): List<Workout> {
        return listOf(workoutRepository.saveAndFlush(
            Workout(null, name, date)
        ))
    }

    // READ
    fun getAllWorkouts(): List<Workout> {
        return workoutRepository.findAll()
    }

    fun getWorkoutById(id: Long): List<Workout> {
        return if (workoutRepository.findById(id).isPresent) {
            listOf(workoutRepository.findById(id).get())
        } else {
            listOf()
        }
    }

    fun getWorkoutsByName(name: String): List<Workout> {
        return workoutRepository.findByName(name)
    }

    fun getWorkoutsByDate(date: LocalDate): List<Workout> {
        return workoutRepository.findByDate(date)
    }

    //  UPDATE
    fun updateWorkoutById(id: Long, name: String, date: LocalDate): List<Workout> {
        val workoutInList = getWorkoutById(id)
        val workout = workoutInList.first()
        workout.name = name
        workout.date = date

        val saved = workoutRepository.saveAndFlush(workout)
        return listOf(saved)
    }

    // DELETE
    fun deleteWorkoutById(id: Long): List<Workout> {
        val workout = getWorkoutById(id)
        if (workout.isNotEmpty()) {
            workoutRepository.deleteById(id)
        }
        return workout
    }
}