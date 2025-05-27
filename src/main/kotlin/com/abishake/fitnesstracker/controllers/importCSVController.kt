package com.abishake.fitnesstracker.controllers

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.service.CSVConverterService
import org.springframework.dao.DataIntegrityViolationException
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

    private val className = EntryController::class

    // READ
    @GetMapping(
        value = ["/get"],
        produces = ["application/json"]
    )
    fun importCSV(): RestResponse<List<Unit>> {
        try {
            csvConverterService.readCSV()
            return RestResponse(true, "Import CSV",  emptyList())
        } catch (e: Exception) {
            throw Exception("Exception in importCSV() >> $className \n $e", e)
        }
    }
}