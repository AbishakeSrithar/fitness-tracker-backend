package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.EntryRepository
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class MainService(
    private val entryRepository: EntryRepository,
    private val workoutRepository: WorkoutRepository,
    private val exerciseRepository: ExerciseRepository,
) {
    // Read Entries
    fun getAllEntries(): List<Entry> {
        return entryRepository.findAll()
    }

    // Read Workouts
    fun getAllWorkouts(): List<Workout> {
        return workoutRepository.findAll()
    }

    fun getWorkoutById(id: Int): Optional<Workout> {
        return workoutRepository.findById(id)
    }

    fun getWorkoutByName(name: String): List<Workout> {
        return workoutRepository.findByName(name)
    }

    fun getWorkoutByDate(localDate: LocalDate): List<Workout> {
        return workoutRepository.findByCreatedAt(localDate)
    }

    // Read Exercises
    fun getAllExercises(): List<Exercise> {
        return exerciseRepository.findAll()
    }
}