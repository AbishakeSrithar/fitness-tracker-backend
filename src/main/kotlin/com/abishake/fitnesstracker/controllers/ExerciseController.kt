package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.service.ExerciseService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/exercise")
class ExerciseController(
    private val exerciseService: ExerciseService
) {
    // CREATE
    @PostMapping(
        value = ["/create"],
        produces = ["application/json"]
    )
    fun createEntry(
        @RequestParam("name") name: String,
        @RequestParam("description") description: String
    ): Exercise {
        return exerciseService.createExercise(name, description)
    }

    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllExercises(): List<Exercise> {
        return exerciseService.getAllExercises()
    }

    @GetMapping(
        value = ["/id"],
        produces = ["application/json"]
    )
    fun getWorkoutById(@RequestParam("id") id: Long): Optional<Exercise> {
        return exerciseService.getExerciseById(id)
    }

    @GetMapping(
        value = ["/name"],
        produces = ["application/json"]
    )
    fun getWorkoutByName(@RequestParam("name") name: String): Optional<Exercise>{
        return exerciseService.getExerciseByName(name)
    }

    // UPDATE
    @PutMapping(
        value = ["/update"],
        produces = ["application/json"]
    )
    fun updateWorkout(
        @RequestParam("id") id: Long,
        @RequestParam("name") name: String,
        @RequestParam("description") description: String
    ): RestResponse {
        return exerciseService.updateExerciseById(id, name, description)
    }

    // DELETE
    @DeleteMapping(
        value = ["/delete"],
        produces = ["application/json"]
    )
    fun deleteExerciseById(
        @RequestParam("id") id: Long
    ): RestResponse {
        return exerciseService.deleteExerciseById(id)
    }
}