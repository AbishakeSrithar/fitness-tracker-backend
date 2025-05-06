package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import org.springframework.stereotype.Service

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {

    private val className = ExerciseService::class

    // CREATE
    fun createExercise(name: String, description: String): Exercise {
        return exerciseRepository.saveAndFlush(
            Exercise(null, name, description)
        )
    }

    // READ
    fun getAllExercises(): List<Exercise> {
        return exerciseRepository.findAll()
    }

    fun getExerciseById(id: Long): Exercise {
        if (exerciseRepository.findById(id).isPresent) {
            return exerciseRepository.findById(id).get()
        } else {
            throw Exception("Exercise Id = $id not found for getExerciseById() >> $className")
        }
    }

    fun getExerciseByName(name: String): Exercise {
        if (exerciseRepository.findByName(name).isPresent) {
            return exerciseRepository.findByName(name).get()
        } else {
            throw Exception("Exercise Name = $name not found for getExerciseByName() >> $className")
        }
    }

    //  UPDATE
    fun updateExerciseById(id: Long, name: String, description: String): Exercise {
        try {
            val exercise = getExerciseById(id)
            exercise.name = name
            exercise.description = description

            exerciseRepository.saveAndFlush(exercise)
            return exercise
        } catch (e: Exception) {
            throw Exception("Exception in updateExerciseById() >> $className", e)
        }
    }

    // DELETE
    fun deleteExerciseById(id: Long): Exercise {
        val exercise = getExerciseById(id)
        try {
            exerciseRepository.deleteById(id)
            return exercise
        } catch (e: Exception) {
            throw Exception("Exception in deleteExerciseById() >> $className", e)
        }
    }
}