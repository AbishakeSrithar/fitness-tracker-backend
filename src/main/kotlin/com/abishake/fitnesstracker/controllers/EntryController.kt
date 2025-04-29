package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.service.EntryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/entry")
class EntryController(
    private val entryService: EntryService,
) {

    private val className = EntryController::class

    // CREATE
    @PostMapping(
        value = ["/create"],
        produces = ["application/json"]
    )
    fun createEntry(
        @RequestParam("workoutId") workoutId: Int,
        @RequestParam("exerciseId") exerciseId: Int,
        @RequestParam("weight") weight: Double,
        @RequestParam("sets") sets: Int,
        @RequestParam("reps") reps: Int
    ): RestResponse<Entry> {
        try {
            val payload = entryService.createEntry(workoutId, exerciseId, weight, sets, reps)
            return RestResponse(true, "Create Entry", payload)
        } catch (e: Exception) {
            throw Exception("Exception in createEntry() >> $className", e)
        }
    }


    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllEntries(): RestResponse<List<Entry>> {
        try {
            val payload = entryService.getAllEntries()
            return RestResponse(true, "Get All Entries", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getAllEntries() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/get/byId"],
        produces = ["application/json"]
    )
    fun getEntryById(@RequestParam("id") id: Long): RestResponse<Entry> {
        try {
            val payload = entryService.getEntryById(id)
            return RestResponse(true, "Get Entry by Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getEntryById() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/get/byWorkoutId"],
        produces = ["application/json"]
    )
    fun getEntryByWorkoutId(@RequestParam("workoutId") workoutId: Int): RestResponse<List<Entry>> {
        try {
            val payload = entryService.getEntriesByWorkoutId(workoutId)
            return RestResponse(true, "Get Entry by Workout Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getEntryByWorkoutId() >> $className", e)
        }
    }

    @GetMapping(
        value = ["/get/byExerciseId"],
        produces = ["application/json"]
    )
    fun getEntryByExerciseId(@RequestParam("exerciseId") exerciseId: Int): RestResponse<List<Entry>> {
        try {
            val payload = entryService.getEntriesByExerciseId(exerciseId)
            return RestResponse(true, "Get Entry by Exercise Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in getEntryByExerciseId() >> $className", e)
        }
    }

    // UPDATE
    @PutMapping(
        value = ["/update"],
        produces = ["application/json"]
    )
    fun updateEntryById(
        @RequestParam("id") id: Long,
        @RequestParam("weight") weight: Double,
        @RequestParam("sets") sets: Int,
        @RequestParam("reps") reps: Int
    ): RestResponse<Entry> {
        try {
            val payload = entryService.updateEntryById(id, weight, sets, reps)
            return RestResponse(true, "Update Entry by Id", payload)
        } catch (e: Exception) {
            throw Exception("Exception in updateEntryById() >> $className", e)
        }
    }

    // DELETE
    @DeleteMapping(
        value = ["/delete"],
        produces = ["application/json"]
    )
    fun deleteEntryById(
        @RequestParam("id") id: Long
    ): RestResponse<String> {
        try {
            val payload = entryService.deleteEntryById(id)
            return RestResponse(true, "Delete Entry by Id", "Deleted Id: $id")
    } catch (e: Exception) {
        throw Exception("Exception in deleteEntryById() >> $className", e)
    }
    }
}