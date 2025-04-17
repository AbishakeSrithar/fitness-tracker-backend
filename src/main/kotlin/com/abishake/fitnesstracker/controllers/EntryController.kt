package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.service.EntryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/entry")
class EntryController(
    private val entryService: EntryService,
) {
    // READ
    @GetMapping(
        value = ["/getAllEntries"],
        produces = ["application/json"]
    )
    fun getAllEntries(): List<Entry> {
        return entryService.getAllEntries()
    }

}