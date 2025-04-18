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
import java.time.LocalDate

import java.time.LocalDateTime
import java.util.Optional

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

    @Test
    fun getWorkoutByIdControllerTest() {
        val workout = Optional.of(Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0)))

        every { workoutService.getWorkoutById(2) } returns workout

        mockMvc.perform(get("/api/workout/id?id=2"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {"id":2,"name":"Pull Day","createdAt":"2025-04-15T13:00:00"}
                """
                )
            )
    }

    @Test
    fun getWorkoutByNameControllerTest() {
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", createdAt = LocalDateTime.of(2025, 4, 14, 13, 0, 0, 0)),
            Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0))
        )

        every { workoutService.getWorkoutByName("Pull Day") } returns workouts

        mockMvc.perform(get("/api/workout/name?name=Pull Day"))
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

    @Test
    fun getWorkoutByDateControllerTest() {
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", createdAt = LocalDateTime.of(2025, 4, 14, 13, 0, 0, 0)),
            Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 14, 18, 0, 0, 0))
        )

        every { workoutService.getWorkoutByDate(LocalDate.of(2025, 4, 14)) } returns workouts

        mockMvc.perform(get("/api/workout/date?date=14/04/2025"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                [{"id":1,"name":"Push Day","createdAt":"2025-04-14T13:00:00"},
                {"id":2,"name":"Pull Day","createdAt":"2025-04-14T18:00:00"}]
                """
                )
            )
    }
}