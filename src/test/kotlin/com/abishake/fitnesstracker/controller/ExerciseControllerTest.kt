package com.abishake.fitnesstracker.controller

import com.abishake.fitnesstracker.controllers.ExerciseController
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.service.ExerciseService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ExerciseController::class)
class ExerciseControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var exerciseService: ExerciseService
    // CREATE
    @Test
    fun `Create Exercise`() {
        //setup
        val exercise = Exercise(1, name = "Deadlift", description = "Die by lifting")

        //given
        every { exerciseService.createExercise("Deadlift", "Die by lifting") } returns exercise

        //when
        mockMvc.perform(post("/api/exercise/create?name=Deadlift&description=Die by lifting"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Create Exercise",
                    "payload":
                        [
                        {
                            "id": 1,
                            "name": "Deadlift",
                            "description": "Die by lifting"
                        }
                        ]
                }
                """
                )
            )
    }

    // READ
    @Test
    fun `Get All Exercises`() {
        //setup
        val exercises = listOf(
            Exercise(
                id = 1,
                name = "Squat",
                description = "A lower body exercise targeting the quads, glutes, and hamstrings."
            ),
            Exercise(
                id = 2,
                name = "Bench Press",
                description = "A chest exercise that targets the pectorals, shoulders, and triceps."
            )
        )

        //given
        every { exerciseService.getAllExercises() } returns exercises

        //when
        mockMvc.perform(get("/api/exercise/get"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                """
                {
                    "success": true,
                    "info": "Get All Exercises",
                    "payload":
                        [
                        {
                            "id": 1,
                            "name": "Squat",
                            "description": "A lower body exercise targeting the quads, glutes, and hamstrings."
                        },
                        {
                            "id": 2,
                            "name": "Bench Press",
                            "description": "A chest exercise that targets the pectorals, shoulders, and triceps."
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Exercise by Id`() {
        //setup
        val exercise = Exercise(
            id = 2,
            name = "Bench Press",
            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
        )

        //given
        every { exerciseService.getExerciseById(2) } returns exercise

        //when
        mockMvc.perform(get("/api/exercise/id?id=2"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Exercise by Id",
                    "payload":
                        [
                        {
                            "id": 2,
                            "name": "Bench Press",
                            "description": "A chest exercise that targets the pectorals, shoulders, and triceps."
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Exercise by Name`() {
        //setup
        val exercise = Exercise(
            id = 2,
            name = "Bench Press",
            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
        )

        //given
        every { exerciseService.getExerciseByName("Bench Press") } returns exercise

        //when
        mockMvc.perform(get("/api/exercise/name?name=Bench Press"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Exercise by Name",
                    "payload":
                        [
                        {
                            "id": 2,
                            "name": "Bench Press",
                            "description": "A chest exercise that targets the pectorals, shoulders, and triceps."
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Update Exercise By Id Controller`() {
        //setup
        val exercise = Exercise(
            id = 2,
            name = "Push Up +",
            description = "Push up with serratus anterior activation"
        )

        //given
        every { exerciseService.updateExerciseById(2, "Push Up +", "Push up with serratus anterior activation") } returns exercise

        //when
        mockMvc.perform(put("/api/exercise/update?id=2&name=Push Up +&description=Push up with serratus anterior activation"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Update Exercise by Id",
                    "payload":
                        [
                        {
                            "id": 2,
                            "name": "Push Up +",
                            "description": "Push up with serratus anterior activation"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Delete Exercise By Id Controller`() {
        //setup
        val exercise = Exercise(
            id = 2,
            name = "Push Up +",
            description = "Push up with serratus anterior activation"
        )

        //given
        every { exerciseService.deleteExerciseById(2) } returns exercise

        //when
        mockMvc.perform(delete("/api/exercise/delete?id=2"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Delete Exercise by Id",
                    "payload":
                        [
                        {
                            "id": 2,
                            "name": "Push Up +",
                            "description": "Push up with serratus anterior activation"
                        }
                        ]
                }
                """
                )
            )
    }
}