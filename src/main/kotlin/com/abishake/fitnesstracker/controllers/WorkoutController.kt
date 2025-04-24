package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.WorkoutService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/workout")
class WorkoutController(
    private val workoutService: WorkoutService,
) {
    // CREATE
    @PostMapping(
        value = ["/create"],
        produces = ["application/json"]
    )
    fun createWorkout(
        @RequestParam("name") name: String,
        @RequestParam("date", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") date: Optional<LocalDate>
    ): Workout {
        return if (date.isPresent) {
            workoutService.createWorkout(name, date.get())
        } else {
            workoutService.createWorkout(name)
        }
    }

    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllWorkouts(): List<Workout> {
        return workoutService.getAllWorkouts()
    }

    @GetMapping(
        value = ["/id"],
        produces = ["application/json"]
    )
    fun getWorkoutById(@RequestParam("id") id: Long): Optional<Workout> {
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
    fun getWorkoutByDate(@RequestParam("date")
                         @DateTimeFormat(pattern = "dd/MM/yyyy") date: LocalDate
    ): List<Workout> {
        return workoutService.getWorkoutByDate(date)
    }

    // UPDATE
    @PutMapping(
        value = ["/update"],
        produces = ["application/json"]
    )
    fun updateWorkout(
        @RequestParam("id") id: Long,
        @RequestParam("name") name: String,
        @RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy") date: LocalDate
    ): RestResponse {
        return workoutService.updateWorkoutById(id, name, date)
    }

    // DELETE
    @DeleteMapping(
        value = ["/delete"],
        produces = ["application/json"]
    )
    fun deleteWorkoutById(
        @RequestParam("id") id: Long
    ): RestResponse {
        return workoutService.deleteWorkoutById(id)
    }
}