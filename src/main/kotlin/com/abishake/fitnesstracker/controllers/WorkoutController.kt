package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.WorkoutService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/workout")
class WorkoutController(
    private val workoutService: WorkoutService,
) {
    // READ
    @GetMapping(
        value = ["/all"],
        produces = ["application/json"]
    )
    fun getAllWorkouts(): List<Workout> {
        return workoutService.getAllWorkouts()
    }

    @GetMapping(
        value = ["/id"],
        produces = ["application/json"]
    )
    fun getWorkoutById(@RequestParam("id") id: Int): Optional<Workout> {
        return workoutService.getWorkoutById(id)
    }

    @GetMapping(
        value = ["/name"],
        produces = ["application/json"]
    )
    fun getWorkoutByName(@RequestParam("name") name: String): List<Workout> {
        return workoutService.getWorkoutByName(name)
    }

    @GetMapping(
        value = ["/date"],
        produces = ["application/json"]
    )
    fun getWorkoutByDate(@RequestParam("localDate")
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) localDate: LocalDate
    ): List<Workout> {
        return workoutService.getWorkoutByDate(localDate)
    }
}