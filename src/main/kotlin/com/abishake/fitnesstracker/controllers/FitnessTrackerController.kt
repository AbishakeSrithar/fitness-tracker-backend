package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.GenericService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FitnessTrackerController(
    private val genericService: GenericService,
) {
//    @GetMapping(
//        value = ["/exercises"],
//        produces = ["application/json"]
//    )
//    fun getExercises(): List<Exercise> {
//        return genericService.getAllExercises()
//    }

    @GetMapping(
        value = ["/workouts"],
        produces = ["application/json"]
    )
    fun getWorkouts(): List<Workout> {
        return genericService.getAllWorkouts()
    }

//    @GetMapping(
//        value = ["/entries"],
//        produces = ["application/json"]
//    )
//    fun getEntries(): List<Entry> {
//        return genericService.getAllEntries()
//    }
}