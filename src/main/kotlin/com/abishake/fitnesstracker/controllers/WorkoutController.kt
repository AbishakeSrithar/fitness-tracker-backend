package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.WorkoutService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/workout")
@CrossOrigin
class WorkoutController(
    private val workoutService: WorkoutService,
) {

    private val className = WorkoutController::class

    // CREATE
    @PostMapping(
        value = ["/create"],
        produces = ["application/json"]
    )
    fun createWorkout(
        @RequestParam("name") name: String,
        @RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy") date: Optional<LocalDate>
    ): RestResponse<List<Workout>> {
        try {
            val payload =  if (date.isPresent) workoutService.createWorkout(name, date.get()) else workoutService.createWorkout(name)
            return RestResponse(true, "Create Workout", payload)
        } catch (e: Exception) {
            throw Exception("Exception in createWorkout() >> $className", e)
        }
    }

    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllWorkouts(): RestResponse<List<Workout>> {
        try {
            val payload =  workoutService.getAllWorkouts()
            return RestResponse(true, "Get All Workouts", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getAllWorkouts() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/get/byId"],
        produces = ["application/json"]
    )
    fun getWorkoutById(@RequestParam("id") id: Long): RestResponse<List<Workout>> {
        try {
            val payload =  workoutService.getWorkoutById(id)
            return RestResponse(true, "Get Workout by Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getWorkoutById() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/get/byName"],
        produces = ["application/json"]
    )
    fun getWorkoutsByName(@RequestParam("name") name: String): RestResponse<List<Workout>> {
        try {
            val payload =  workoutService.getWorkoutsByName(name)
            return RestResponse(true, "Get Workouts by Name", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getWorkoutsByName() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/get/byDate"],
        produces = ["application/json"]
    )
    fun getWorkoutsByDate(@RequestParam("date")
                         @DateTimeFormat(pattern = "dd/MM/yyyy") date: LocalDate
    ): RestResponse<List<Workout>> {
        try {
            val payload =  workoutService.getWorkoutsByDate(date)
            return RestResponse(true, "Get Workouts by Date", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getWorkoutsByDate() >> $className", e)
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
        @RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy") date: LocalDate
    ): RestResponse<List<Workout>> {
        try {
            val payload =  workoutService.updateWorkoutById(id, name, date)
            return RestResponse(true, "Update Workout by Id", payload)
        } catch (e: NoSuchElementException) {
            return RestResponse(false, "Error in Update Workout by Id: Workout Id not found", listOf())
        } catch (e: Exception) {
            throw Exception("Exception in updateWorkoutById() >> $className", e)
        }
    }

    // DELETE
    @DeleteMapping(
        value = ["/delete"],
        produces = ["application/json"]
    )
    fun deleteWorkoutById(
        @RequestParam("id") id: Long
    ): RestResponse<List<Workout>> {
        try {
            val payload =  workoutService.deleteWorkoutById(id)
            return if (payload.isNotEmpty()) {
                RestResponse(true, "Delete Workout by Id", payload)
            } else {
                RestResponse(false, "Error in Delete Workout by Id: Workout Id not found", listOf())
            }
        } catch (e: DataIntegrityViolationException) {
            return RestResponse(false, "Error in Delete Workout by Id: DataIntegrityViolationException. WorkoutId being used in a current Entry, cannot delete.", listOf())
        } catch (e: Exception) {
            throw Exception("Exception in deleteWorkoutById() >> $className \n $e", e)
        }
    }
}