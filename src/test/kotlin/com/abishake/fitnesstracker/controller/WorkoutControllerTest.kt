package com.abishake.fitnesstracker.controller

import com.abishake.fitnesstracker.controllers.WorkoutController
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.service.WorkoutService
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

@WebMvcTest(WorkoutController::class)
class WorkoutControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var workoutService: WorkoutService

    @Test
    fun getAllWorkoutsControllerTest() {
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", createdAt = LocalDateTime.of(2025, 4, 14, 13, 0, 0, 0)),
            Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0))
        )

        every { workoutService.getAllWorkouts() } returns workouts

        mockMvc.perform(get("/api/workout/all"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                """
                [{"id":1,"name":"Push Day","createdAt":"2025-04-14T13:00:00"},
                {"id":2,"name":"Pull Day","createdAt":"2025-04-15T13:00:00"}]
                """
                )
            )
    }
}