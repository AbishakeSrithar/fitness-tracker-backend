package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {
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

    fun getExerciseById(id: Long): Optional<Exercise> {
        return exerciseRepository.findById(id)
    }

    fun getExerciseByName(name: String): Optional<Exercise> {
        return exerciseRepository.findByName(name)
    }

    //  UPDATE
    fun updateExerciseById(id: Long, name: String, description: String): RestResponse {
        if (getExerciseById(id).isPresent) {
            val exercise = getExerciseById(id).get()
            exercise.name = name
            exercise.description = description

            try {
                exerciseRepository.saveAndFlush(exercise)
                return RestResponse("True", "Successfully updated Exercise with ID: $id to have name=$name, description=$description")
            } catch (e: Exception) {
                return RestResponse("False", "Error while updating Exercise with ID: $id")
            }
        } else {
            return RestResponse("False", "Cannot find Exercise with ID: $id to update")
        }
    }

    // DELETE
    fun deleteExerciseById(id: Long): RestResponse {
        if (exerciseRepository.findById(id).isPresent) {
            exerciseRepository.deleteById(id)
            return RestResponse("True", "Successfully deleted Exercise with ID: $id")
        } else {
            return RestResponse("False", "Exercise ID: $id not found")
        }
    }
}