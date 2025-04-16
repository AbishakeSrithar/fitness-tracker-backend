package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.MainService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api")
class MainController(
    private val mainService: MainService,
) {
    @GetMapping(
        value = ["/getAllExercises"],
        produces = ["application/json"]
    )
    fun getAllExercises(): List<Exercise> {
        return mainService.getAllExercises()
    }

    @GetMapping(
        value = ["/getAllWorkouts"],
        produces = ["application/json"]
    )
    fun getAllWorkouts(): List<Workout> {
        return mainService.getAllWorkouts()
    }

    @GetMapping(
        value = ["/getWorkoutById"],
        produces = ["application/json"]
    )
    fun getWorkoutById(@RequestParam("id") id: Int): Optional<Workout> {
        return mainService.getWorkoutById(id)
    }

    @GetMapping(
        value = ["/getWorkoutByName"],
        produces = ["application/json"]
    )
    fun getWorkoutByName(@RequestParam("name") name: String): List<Workout> {
        return mainService.getWorkoutByName(name)
    }

    @GetMapping(
        value = ["/getWorkoutByDate"],
        produces = ["application/json"]
    )
    fun getWorkoutByDate(@RequestParam("localDate")
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) localDate: LocalDate
    ): List<Workout> {
        return mainService.getWorkoutByDate(localDate)
    }

    @GetMapping(
        value = ["/getAllEntries"],
        produces = ["application/json"]
    )
    fun getAllEntries(): List<Entry> {
        return mainService.getAllEntries()
    }
}