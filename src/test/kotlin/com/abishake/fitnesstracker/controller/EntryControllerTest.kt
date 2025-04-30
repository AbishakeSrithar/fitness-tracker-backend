package com.abishake.fitnesstracker.controller

import com.abishake.fitnesstracker.controllers.EntryController
import com.abishake.fitnesstracker.models.Entry
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

@WebMvcTest(EntryController::class)
class EntryControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var entryService: EntryService
    // CREATE
    @Test
    fun `Create Entry`() {
        //setup
        val entry = Entry(1, workoutId = 2, exerciseId = 3,  weight = 60.2, sets = 4, reps = 10)

        //given
        every { entryService.createEntry(2, 3, 60.2, 4, 10) } returns entry

        //when
        mockMvc.perform(post("/api/entry/create?workoutId=2&exerciseId=3&weight=60.2&sets=4&reps=10"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Create Entry",
                    "payload": 
                        {
                            "id": 1,
                            "workoutId": 2,
                            "exerciseId": 3,
                            "weight": 60.2,
                            "sets": 4,
                            "reps": 10
                        }
                }
                """
                )
            )
    }

    // READ
    @Test
    fun `Get All Entries`() {
        //setup
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )

        //given
        every { entryService.getAllEntries() } returns entries

        //when
        mockMvc.perform(get("/api/entry/get"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get All Entries",
                    "payload":
                        [
                        {
                            "id": 1,
                            "workoutId": 1,
                            "exerciseId": 1,
                            "weight": 60.0,
                            "sets": 3,
                            "reps": 10
                        },
                        {
                            "id": 2,
                            "workoutId": 1,
                            "exerciseId": 2,
                            "weight": 60.0,
                            "sets": 4,
                            "reps": 8
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Entry by Id`() {
        //setup
        val entry = Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10)

        //given
        every { entryService.getEntryById(1) } returns entry

        //when
        mockMvc.perform(get("/api/entry/get/byId?id=1"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Entry by Id",
                    "payload":
                        {
                            "id": 1,
                            "workoutId": 1,
                            "exerciseId": 1,
                            "weight": 60.0,
                            "sets": 3,
                            "reps": 10
                        }
                }
                """
                )
            )
    }

    @Test
    fun `Get Entries by Workout Id`() {
        //setup
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )

        //given
        every { entryService.getEntriesByWorkoutId(1) } returns entries

        //when
        mockMvc.perform(get("/api/entry/get/byWorkoutId?workoutId=1"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Entry by Workout Id",
                    "payload":
                        [
                        {
                            "id": 1,
                            "workoutId": 1,
                            "exerciseId": 1,
                            "weight": 60.0,
                            "sets": 3,
                            "reps": 10
                        },
                        {
                            "id": 2,
                            "workoutId": 1,
                            "exerciseId": 2,
                            "weight": 60.0,
                            "sets": 4,
                            "reps": 8
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Entries by Exercise Id`() {
        //setup
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 2, exerciseId = 1, weight = 60.0, sets = 4, reps = 8)
        )

        //given
        every { entryService.getEntriesByExerciseId(1) } returns entries

        //when
        mockMvc.perform(get("/api/entry/get/byExerciseId?exerciseId=1"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                "success": true,
                "info": "Get Entry by Exercise Id",
                "payload":
                    [
                    {
                        "id": 1,
                        "workoutId": 1,
                        "exerciseId": 1,
                        "weight": 60.0,
                        "sets": 3,
                        "reps": 10
                    },
                    {
                        "id": 2,
                        "workoutId": 2,
                        "exerciseId": 1,
                        "weight": 60.0,
                        "sets": 4,
                        "reps": 8
                    }
                    ]
                }
                """
                )
            )
    }

    @Test
    fun `Update Entry By Id Controller`() {
        //setup
        val entry = Entry(id = 2, workoutId = 2, exerciseId = 1, weight = 70.0, sets = 5, reps = 12)

        //given
        every { entryService.updateEntryById(2, 70.0, 5, 12) } returns entry

        //when
        mockMvc.perform(put("/api/entry/update?id=2&weight=70.0&sets=5&reps=12"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                "success": true,
                "info": "Update Entry by Id",
                "payload":
                    {
                        "id": 2,
                        "workoutId": 2,
                        "exerciseId": 1,
                        "weight": 70.0,
                        "sets": 5,
                        "reps": 12
                    }
                }
                """
                )
            )
    }

    @Test
    fun `Delete Entry By Id Controller`() {
        //given
        every { entryService.deleteEntryById(2) } returns Unit

        //when
        mockMvc.perform(delete("/api/entry/delete?id=2"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Delete Entry by Id",
                    "payload": "Deleted Id: 2"
                }
                """
                )
            )
    }
}