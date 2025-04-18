package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.ExerciseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/exercise")
class ExerciseController(
    private val exerciseService: ExerciseService
) {
    // READ
    @GetMapping(
        value = ["/all"],
        produces = ["application/json"]
    )
    fun getAllExercises(): List<Exercise> {
        return exerciseService.getAllExercises()
    }

    @GetMapping(
        value = ["/id"],
        produces = ["application/json"]
    )
    fun getWorkoutById(@RequestParam("id") id: Int): Optional<Exercise> {
        return exerciseService.getExerciseById(id)
    }

    @GetMapping(
        value = ["/name"],
        produces = ["application/json"]
    )
    fun getWorkoutByName(@RequestParam("name") name: String): Optional<Exercise>{
        return exerciseService.getExerciseByName(name)
    }
}