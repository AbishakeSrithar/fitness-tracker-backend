package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {

    private val className = ExerciseService::class

    // CREATE
    fun createExercise(name: String, description: String): Exercise {
        val existingEntry = exerciseRepository.findByNameAndDescription(name, description)
        return if (existingEntry.isEmpty()) {
            exerciseRepository.saveAndFlush(
                Exercise(null, name, description)
            )
        } else {
            existingEntry[0]
        }
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

    fun getExerciseByName(name: String): List<Exercise> {
            return exerciseRepository.findByName(name)
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
            throw Exception("Exception in updateExerciseById() >> $className \n $e", e)
        }
    }

    // DELETE
    fun deleteExerciseById(id: Long): Exercise {
        val exercise = getExerciseById(id)
        exerciseRepository.deleteById(id)
        return exercise
    }
}