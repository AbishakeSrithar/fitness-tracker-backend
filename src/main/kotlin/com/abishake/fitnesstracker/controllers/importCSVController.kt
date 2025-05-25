package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.service.CSVConverterService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/import")
@CrossOrigin
class importCSVController (
    private val csvConverterService: CSVConverterService
) {
    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun getAllEntries() {
        csvConverterService.readCSV()
    }
}