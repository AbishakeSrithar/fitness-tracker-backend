package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.service.EntryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/entry")
class EntryController(
    private val entryService: EntryService,
) {
    // READ
    @GetMapping(
        value = ["/all"],
        produces = ["application/json"]
    )
    fun getAllEntries(): List<Entry> {
        return entryService.getAllEntries()
    }

    @GetMapping(
        value = ["/id"],
        produces = ["application/json"]
    )
    fun getEntryById(@RequestParam("id") id: Int): Optional<Entry> {
        return entryService.getEntryById(id)
    }

    @GetMapping(
        value = ["/workoutId"],
        produces = ["application/json"]
    )
    fun getEntryByWorkoutId(@RequestParam("workoutId") workoutId: Int): List<Entry> {
        return entryService.getEntriesByWorkoutId(workoutId)
    }

    @GetMapping(
        value = ["/exerciseId"],
        produces = ["application/json"]
    )
    fun getEntryByExerciseId(@RequestParam("exerciseId") exerciseId: Int): List<Entry> {
        return entryService.getEntriesByExerciseId(exerciseId)
    }

}