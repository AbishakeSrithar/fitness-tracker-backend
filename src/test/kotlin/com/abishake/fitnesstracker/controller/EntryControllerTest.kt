package com.abishake.fitnesstracker.controller

import com.abishake.fitnesstracker.controllers.EntryController
import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.EntryService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import java.time.LocalDateTime

@WebMvcTest(EntryController::class)
class EntryControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var entryService: EntryService

    @Test
    fun getAllEntriesControllerTest() {
        val workout_full_body = Workout(
            id = 1,
            name = "Full Body Day",
            createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0)
        )
        val exercise_squat = Exercise(
            id = 1,
            name = "Squat",
            description = "A lower body exercise targeting the quads, glutes, and hamstrings."
        )
        val exercise_bench = Exercise(
            id = 2,
            name = "Bench Press",
            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
        )
        val entries = listOf(
            Entry(workout = workout_full_body, exercise = exercise_squat, weight = 60.0, sets = 3, reps = 10),
            Entry(workout = workout_full_body, exercise = exercise_bench, weight = 60.0, sets = 4, reps = 8)
        )

        every { entryService.getAllEntries() } returns entries

        mockMvc.perform(get("/api/entry/getAllEntries"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                """
                [{"id":0,
                "workout":{"id":1,"name":"Full Body Day","createdAt":"2025-04-15T13:00:00"},
                "exercise":{"id":1,"name":"Squat","description":"A lower body exercise targeting the quads, glutes, and hamstrings."},
                "weight":60.0,
                "sets":3,
                "reps":10
                },
                {"id":0,
                "workout":{"id":1, "name":"Full Body Day", "createdAt":"2025-04-15T13:00:00"},
                "exercise":{"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."},
                "weight":60.0,
                "sets":4,
                "reps":8}]
                """
                )
            )
    }
}