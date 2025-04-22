package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {
    // CREATE
    fun createExercise(name: String, description: String): Exercise {
        return exerciseRepository.save(
            Exercise(null, name, description)
        )
    }

    // READ
    fun getAllExercises(): List<Exercise> {
        return exerciseRepository.findAll()
    }

    fun getExerciseById(id: Int): Optional<Exercise> {
        return exerciseRepository.findById(id)
    }

    fun getExerciseByName(name: String): Optional<Exercise> {
        return exerciseRepository.findByName(name)
    }
}