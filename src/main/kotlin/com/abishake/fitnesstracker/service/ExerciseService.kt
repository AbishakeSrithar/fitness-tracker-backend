package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import org.springframework.stereotype.Service

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {
    // READ
    fun getAllExercises(): List<Exercise> {
        return exerciseRepository.findAll()
    }
}