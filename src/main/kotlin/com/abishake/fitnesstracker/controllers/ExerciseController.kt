package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.service.ExerciseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}