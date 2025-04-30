//package com.abishake.fitnesstracker.controller
//
//import com.abishake.fitnesstracker.controllers.EntryController
//import com.abishake.fitnesstracker.models.Entry
//import com.abishake.fitnesstracker.models.RestResponse
//import com.abishake.fitnesstracker.service.EntryService
//import com.ninjasquad.springmockk.MockkBean
//import io.mockk.every
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
//
//import java.util.*
//
//@WebMvcTest(EntryController::class)
//class EntryControllerTest(
//    @Autowired val mockMvc: MockMvc
//) {
//
//    @MockkBean
//    lateinit var entryService: EntryService
//    // CREATE
//    @Test
//    fun `Create Entry`() {
//        val entry = Entry(1, workoutId = 2, exerciseId = 3,  weight = 60.2, sets = 4, reps = 10)
//
//        every { entryService.createEntry(2, 3, 60.2, 4, 10) } returns entry
//
//        mockMvc.perform(post("/api/entry/create?workoutId=2&exerciseId=3&weight=60.2&sets=4&reps=10"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":1, "workoutId":2, "exerciseId": 3, "weight":60.2, "sets":4, "reps":10}
//                """
//                )
//            )
//    }
//
//    // READ
//    @Test
//    fun `Get All Entries`() {
//        val entries = listOf(
//            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
//            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
//        )
//
//        every { entryService.getAllEntries() } returns entries
//
//        mockMvc.perform(get("/api/entry/get"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                """
//                [{"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10},
//                {"id":2, "workoutId":1, "exerciseId": 2, "weight":60.0, "sets":4, "reps":8}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Entry by Id`() {
//        val entry = Optional.of(Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10))
//
//        every { entryService.getEntryById(1) } returns entry
//
//        mockMvc.perform(get("/api/entry/get/byId?id=1"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Entries by Workout Id`() {
//        val entries = listOf(
//            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
//            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
//        )
//
//        every { entryService.getEntriesByWorkoutId(1) } returns entries
//
//        mockMvc.perform(get("/api/entry/get/byWorkoutId?workoutId=1"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                [{"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10},
//                {"id":2, "workoutId":1, "exerciseId": 2, "weight":60.0, "sets":4, "reps":8}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Entries by Exercise Id`() {
//        val entries = listOf(
//            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
//            Entry(id = 2, workoutId = 2, exerciseId = 1, weight = 60.0, sets = 4, reps = 8)
//        )
//
//        every { entryService.getEntriesByExerciseId(1) } returns entries
//
//        mockMvc.perform(get("/api/entry/get/byExerciseId?exerciseId=1"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                [{"id":1, "workoutId":1, "exerciseId": 1, "weight":60.0, "sets":3, "reps":10},
//                {"id":2, "workoutId":2, "exerciseId": 1, "weight":60.0, "sets":4, "reps":8}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Update Entry By Id Controller`() {
//
//        every { entryService.updateEntryById(2, 70.0, 5, 12) } returns RestResponse("True", "Successfully updated Entry with ID: 2 to have weight=70.0, sets=5, reps=12")
//
//        mockMvc.perform(put("/api/entry/update?id=2&weight=70.0&sets=5&reps=12"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"isOk": "True", "reason": "Successfully updated Entry with ID: 2 to have weight=70.0, sets=5, reps=12"}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Delete Entry By Id Controller`() {
//
//        every { entryService.deleteEntryById(2) } returns RestResponse("True", "Successfully deleted Entry with ID: 2")
//
//        mockMvc.perform(delete("/api/entry/delete?id=2"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"isOk": "True", "reason": "Successfully deleted Entry with ID: 2"}
//                """
//                )
//            )
//    }
//}