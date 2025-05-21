package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import org.springframework.stereotype.Service

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {

    // CREATE
    fun createExercise(name: String, description: String): List<Exercise> {
        val existingEntry = exerciseRepository.findByNameAndDescription(name, description)
        return if (existingEntry.isEmpty()) {
            val saved = exerciseRepository.saveAndFlush(
                Exercise(null, name, description)
            )
            return listOf(saved)
        } else {
            listOf(existingEntry.first())
        }
    }

    // READ
    fun getAllExercises(): List<Exercise> {
        return exerciseRepository.findAll()
    }

    fun getExerciseById(id: Long): List<Exercise> {
        return if (exerciseRepository.findById(id).isPresent) {
            listOf(exerciseRepository.findById(id).get())
        } else {
            listOf()
        }
    }

    fun getExerciseByName(name: String): List<Exercise> {
            return exerciseRepository.findByName(name)
    }

    //  UPDATE
    fun updateExerciseById(id: Long, name: String, description: String): List<Exercise> {
        val exerciseInList = getExerciseById(id)
        val exercise = exerciseInList.first()
        exercise.name = name
        exercise.description = description

        val saved = exerciseRepository.saveAndFlush(exercise)
        return listOf(saved)
    }

    // DELETE
    fun deleteExerciseById(id: Long): List<Exercise> {
        val exercise = getExerciseById(id)
        if (exercise.isNotEmpty()) {
            exerciseRepository.deleteById(id)
        }
        return exercise
    }
}