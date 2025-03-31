package com.abishake.fitnesstracker.fitness_tracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FitnessTrackerController {

    @GetMapping(
        value = ["/exercises"],
        produces = ["application/json"]
    )
    fun getExercises(): String {
        return "Exercises data"
    }

    @GetMapping(
        value = ["/workouts"],
        produces = ["application/json"]
    )
    fun getWorkouts(): String {
        return "Workouts data"
    }

    @GetMapping(
        value = ["/entries"],
        produces = ["application/json"]
    )
    fun getEntries(): String {
        return "Entries data"
    }
}