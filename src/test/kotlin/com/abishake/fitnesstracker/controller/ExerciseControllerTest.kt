package com.abishake.fitnesstracker.controller

import com.abishake.fitnesstracker.controllers.ExerciseController
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.ExerciseService
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
import java.util.*

@WebMvcTest(ExerciseController::class)
class ExerciseControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var exerciseService: ExerciseService

    @Test
    fun getAllExercisesControllerTest() {
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

        every { exerciseService.getAllExercises() } returns exercises

        mockMvc.perform(get("/api/exercise/all"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                """
                [{"id":1,"name":"Squat","description":"A lower body exercise targeting the quads, glutes, and hamstrings."},
                {"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."}]
                """
                )
            )
    }

    @Test
    fun getExerciseByIdControllerTest() {
        val exercise = Optional.of(Exercise(
            id = 2,
            name = "Bench Press",
            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
        ))

        every { exerciseService.getExerciseById(2) } returns exercise

        mockMvc.perform(get("/api/exercise/id?id=2"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."}
                """
                )
            )
    }

    @Test
    fun getExerciseByNameControllerTest() {
        val exercise = Optional.of(Exercise(
            id = 2,
            name = "Bench Press",
            description = "A chest exercise that targets the pectorals, shoulders, and triceps."
        ))


        every { exerciseService.getExerciseByName("Bench Press") } returns exercise

        mockMvc.perform(get("/api/exercise/name?name=Bench Press"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {"id":2,"name":"Bench Press","description":"A chest exercise that targets the pectorals, shoulders, and triceps."}
                """
                )
            )
    }
}