package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.service.ExerciseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/exercise")
@CrossOrigin
class ExerciseController(
    private val exerciseService: ExerciseService
) {

    private val className = ExerciseController::class

    // CREATE
    @PostMapping(
        value = ["/create"],
        produces = ["application/json"]
    )
    fun createExercise(
        @RequestParam("name") name: String,
        @RequestParam("description") description: String
    ): RestResponse<Exercise> {
        try {
            val payload = exerciseService.createExercise(name, description)
            return RestResponse(true, "Create Exercise", payload)
        } catch (e: Exception) {
            throw Exception("Exception in createExercise() >> $className", e)
        }
    }

    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllExercises(): RestResponse<List<Exercise>> {
        try {
            val payload = exerciseService.getAllExercises()
            return RestResponse(true, "Get All Exercises", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getAllExercises() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/id"],
        produces = ["application/json"]
    )
    fun getExerciseById(@RequestParam("id") id: Long): RestResponse<Exercise> {
        try {
            val payload = exerciseService.getExerciseById(id)
            return RestResponse(true, "Get Exercise by Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getExerciseById() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/name"],
        produces = ["application/json"]
    )
    fun getExerciseByName(@RequestParam("name") name: String): RestResponse<Exercise>{
        try {
            val payload = exerciseService.getExerciseByName(name)
            return RestResponse(true, "Get Exercise by Name", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getExerciseByName() >> $className", e)
        }
    }

    // UPDATE
    @PutMapping(
        value = ["/update"],
        produces = ["application/json"]
    )
    fun updateWorkoutById(
        @RequestParam("id") id: Long,
        @RequestParam("name") name: String,
        @RequestParam("description") description: String
    ): RestResponse<Exercise> {
        try {
            val payload = exerciseService.updateExerciseById(id, name, description)
            return RestResponse(true, "Update Exercise by Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in updateWorkoutById() >> $className", e)
        }
    }

    // DELETE
    @DeleteMapping(
        value = ["/delete"],
        produces = ["application/json"]
    )
    fun deleteExerciseById(
        @RequestParam("id") id: Long
    ): RestResponse<String> {
        try {
            exerciseService.deleteExerciseById(id)
            return RestResponse(true, "Delete Exercise by Id", "Deleted Id: $id")
        } catch (e: Exception) {
            throw Exception("Exception in deleteExerciseById() >> $className", e)
        }
    }
}