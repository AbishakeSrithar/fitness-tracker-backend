package com.abishake.fitnesstracker.fitness_tracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FitnessTrackerController {

    @GetMapping("/workouts")
    fun getWorkouts(): String {
        return "Workouts data"
    }
}