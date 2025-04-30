//package com.abishake.fitnesstracker.controller
//
//import com.abishake.fitnesstracker.controllers.ExerciseController
//import com.abishake.fitnesstracker.models.Exercise
//import com.abishake.fitnesstracker.models.RestResponse
//import com.abishake.fitnesstracker.service.ExerciseService
//import com.ninjasquad.springmockk.MockkBean
//import io.mockk.every
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
//import java.util.*
//
//@WebMvcTest(ExerciseController::class)
//class ExerciseControllerTest(
//    @Autowired val mockMvc: MockMvc
//) {
//
//    @MockkBean
//    lateinit var exerciseService: ExerciseService
//    // CREATE
//    @Test
//    fun `Create Exercise`() {
//        val exercise = Exercise(1, name = "Deadlift", description = "Die by lifting")
//
//        every { exerciseService.createExercise("Deadlift", "Die by lifting") } returns exercise
//
//        mockMvc.perform(post("/api/exercise/create?name=Deadlift&description=Die by lifting"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":1, "name": "Deadlift", "description": "Die by lifting"}
//                """
//                )
//            )
//    }
//
//    // READ
//    @Test
//    fun `Get All Exercises`() {
//        val exercises = listOf(
//            Exercise(
//                id = 1,
//                name = "Squat",
//                description = "A lower body exercise targeting the quads, glutes, and hamstrings."
//            ),
//            Exercise(
//                id = 2,
//                name = "Bench Press",
//                description = "A chest exercise that targets the pectorals, shoulders, and triceps."
//            )
//        )
//
//        every { exerciseService.getAllExercises() } returns exercises
//
//        mockMvc.perform(get("/api/exercise/get"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                """
//                [{"id":1,"name":"Squat","description":"A lower body exercise targeting the quads, glutes, and hamstrings."},
//                {"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Exercise by Id`() {
//        val exercise = Optional.of(Exercise(
//            id = 2,
//            name = "Bench Press",
//            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
//        ))
//
//        every { exerciseService.getExerciseById(2) } returns exercise
//
//        mockMvc.perform(get("/api/exercise/id?id=2"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Exercise by Name`() {
//        val exercise = Optional.of(Exercise(
//            id = 2,
//            name = "Bench Press",
//            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
//        ))
//
//
//        every { exerciseService.getExerciseByName("Bench Press") } returns exercise
//
//        mockMvc.perform(get("/api/exercise/name?name=Bench Press"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Update Exercise By Id Controller`() {
//
//        every { exerciseService.updateExerciseById(2, "Push Up +", "Push up with serratus anterior activation") } returns RestResponse("True", "Successfully updated Entry with ID: 2 to have name=70.0, description=Push up with serratus anterior activation")
//
//        mockMvc.perform(put("/api/exercise/update?id=2&name=Push Up +&description=Push up with serratus anterior activation"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"isOk": "True", "reason": "Successfully updated Entry with ID: 2 to have name=70.0, description=Push up with serratus anterior activation"}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Delete Exercise By Id Controller`() {
//
//        every { exerciseService.deleteExerciseById(2) } returns RestResponse("True", "Successfully deleted Exercise with ID: 2")
//
//        mockMvc.perform(delete("/api/exercise/delete?id=2"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"isOk": "True", "reason": "Successfully deleted Exercise with ID: 2"}
//                """
//                )
//            )
//    }
//}