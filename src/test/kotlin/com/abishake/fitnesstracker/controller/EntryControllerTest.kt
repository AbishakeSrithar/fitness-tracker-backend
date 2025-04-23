package com.abishake.fitnesstracker.controller

import com.abishake.fitnesstracker.controllers.EntryController
import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.service.EntryService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import java.util.*

@WebMvcTest(EntryController::class)
class EntryControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var entryService: EntryService
    // CREATE
    @Test
    fun createEntryControllerTest() {
        val entry = Entry(1, workoutId = 2, exerciseId = 3,  weight = 60.2, sets = 4, reps = 10)

        every { entryService.createEntry(2, 3, 60.2, 4, 10) } returns entry

        mockMvc.perform(post("/api/entry/create?workoutId=2&exerciseId=3&weight=60.2&sets=4&reps=10"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {"id":1, "workoutId":2, "exerciseId": 3, "weight":60.2, "sets":4, "reps":10}
                """
                )
            )
    }

    // READ
    @Test
    fun getAllEntriesControllerTest() {
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )

        every { entryService.getAllEntries() } returns entries

        mockMvc.perform(get("/api/entry/get"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                """
                [{"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10},
                {"id":2, "workoutId":1, "exerciseId": 2, "weight":60.0, "sets":4, "reps":8}]
                """
                )
            )
    }

    @Test
    fun getEntryByIdControllerTest() {
        val entry = Optional.of(Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10))

        every { entryService.getEntryById(1) } returns entry

        mockMvc.perform(get("/api/entry/get/byId?id=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10}
                """
                )
            )
    }

    @Test
    fun getEntriesByWorkoutIdControllerTest() {
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )

        every { entryService.getEntriesByWorkoutId(1) } returns entries

        mockMvc.perform(get("/api/entry/get/byWorkoutId?workoutId=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                [{"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10},
                {"id":2, "workoutId":1, "exerciseId": 2, "weight":60.0, "sets":4, "reps":8}]
                """
                )
            )
    }

    @Test
    fun getEntriesByExerciseIdControllerTest() {
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 2, exerciseId = 1, weight = 60.0, sets = 4, reps = 8)
        )

        every { entryService.getEntriesByExerciseId(1) } returns entries

        mockMvc.perform(get("/api/entry/get/byExerciseId?exerciseId=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                [{"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10},
                {"id":2, "workoutId":2, "exerciseId": 1, "weight":60.0, "sets":4, "reps":8}]
                """
                )
            )
    }

    @Test
    fun `Delete Entry By Id Controller Test`() {

        every { entryService.deleteEntryById(2) } returns RestResponse("True", "Successfully deleted Entry with ID: 2")

        mockMvc.perform(delete("/api/entry/delete?id=2"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {"isOk": "True", "reason": "Successfully deleted Entry with ID: 2"}
                """
                )
            )
    }
}