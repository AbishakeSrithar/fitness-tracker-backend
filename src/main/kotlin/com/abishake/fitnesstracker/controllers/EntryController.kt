package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.service.EntryService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/entry")
class EntryController(
    private val entryService: EntryService,
) {
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
    ): Entry {
        return entryService.createEntry(workoutId, exerciseId, weight, sets, reps)
    }

    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllEntries(): List<Entry> {
        return entryService.getAllEntries()
    }

    @GetMapping(
        value = ["/get/byId"],
        produces = ["application/json"]
    )
    fun getEntryById(@RequestParam("id") id: Long): Optional<Entry> {
        return entryService.getEntryById(id)
    }

    @GetMapping(
        value = ["/get/byWorkoutId"],
        produces = ["application/json"]
    )
    fun getEntryByWorkoutId(@RequestParam("workoutId") workoutId: Int): List<Entry> {
        return entryService.getEntriesByWorkoutId(workoutId)
    }

    @GetMapping(
        value = ["/get/byExerciseId"],
        produces = ["application/json"]
    )
    fun getEntryByExerciseId(@RequestParam("exerciseId") exerciseId: Int): List<Entry> {
        return entryService.getEntriesByExerciseId(exerciseId)
    }

    // UPDATE
    @PutMapping(
        value = ["/update"],
        produces = ["application/json"]
    )
    fun updateEntry(
        @RequestParam("id") id: Long,
        @RequestParam("weight") weight: Double,
        @RequestParam("sets") sets: Int,
        @RequestParam("reps") reps: Int
    ): RestResponse {
        return entryService.updateEntryById(id, weight, sets, reps)
    }

    // DELETE
    @DeleteMapping(
        value = ["/delete"],
        produces = ["application/json"]
    )
    fun deleteEntryById(
        @RequestParam("id") id: Long
    ): RestResponse {
        return entryService.deleteEntryById(id)
    }
}