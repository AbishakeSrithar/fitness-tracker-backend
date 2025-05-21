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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(WorkoutController::class)
class WorkoutControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var workoutService: WorkoutService
    // CREATE
    @Test
    fun `Create Workout`() {
        //setup
        val date = LocalDate.of(2025, 4, 14)
        val workout = Workout(1, name = "Push Day", date = date)

        //given
        every { workoutService.createWorkout("Push Day", date) } returns listOf(workout)

        //when
        mockMvc.perform(post("/api/workout/create?name=Push Day&date=2025-04-14"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Create Workout",
                    "payload":
                        [
                        {
                            "id": 1,
                            "name": "Push Day",
                            "date":"2025-04-14"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get All Workouts`() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", date = LocalDate.of(2025, 4, 14)),
            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15))
        )

        //given
        every { workoutService.getAllWorkouts() } returns workouts

        //when
        mockMvc.perform(get("/api/workout/get"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                """
                {
                    "success": true,
                    "info": "Get All Workouts",
                    "payload":
                        [
                        {
                            "id": 1,
                            "name": "Push Day",
                            "date": "2025-04-14"
                        },
                        {
                            "id": 2,
                            "name": "Pull Day",
                            "date":"2025-04-15"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Workout by Id`() {
        //setup
        val workout = Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15))

        //given
        every { workoutService.getWorkoutById(2) } returns listOf(workout)

        //when
        mockMvc.perform(get("/api/workout/get/byId?id=2"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Workout by Id",
                    "payload":
                        [
                        {
                            "id": 2,
                            "name": "Pull Day",
                            "date":"2025-04-15"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Workouts by Name`() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Pull Day", date = LocalDate.of(2025, 4, 14)),
            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15))
        )

        //given
        every { workoutService.getWorkoutsByName("Pull Day") } returns workouts

        //when
        mockMvc.perform(get("/api/workout/get/byName?name=Pull Day"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Workouts by Name",
                    "payload":
                        [
                        {
                            "id": 1,
                            "name": "Pull Day",
                            "date": "2025-04-14"
                        },
                        {
                            "id": 2,
                            "name": "Pull Day",
                            "date":"2025-04-15"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Get Workouts by Date`() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", date = LocalDate.of(2025, 4, 14)),
            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 14))
        )

        //given
        every { workoutService.getWorkoutsByDate(LocalDate.of(2025, 4, 14)) } returns workouts

        //when
        mockMvc.perform(get("/api/workout/get/byDate?date=14/04/2025"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Get Workouts by Date",
                    "payload":
                        [
                        {
                            "id": 1,
                            "name": "Push Day",
                            "date": "2025-04-14"
                        },
                        {
                            "id": 2,
                            "name": "Pull Day",
                            "date":"2025-04-14"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Update Workout by Id`() {
        //setup
        val workout = Workout(id = 2, name = "Morning Routine", date = LocalDate.of(2025, 4, 12))

        //given
        every { workoutService.updateWorkoutById(2, "Morning Routine", LocalDate.of(2025, 4, 12)) } returns listOf(workout)

        //when
        mockMvc.perform(put("/api/workout/update?id=2&name=Morning Routine&date=2025-04-12"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Update Workout by Id",
                    "payload":
                        [
                        {
                            "id": 2,
                            "name": "Morning Routine",
                            "date":"2025-04-12"
                        }
                        ]
                }
                """
                )
            )
    }

    @Test
    fun `Delete Workout By Id`() {
        //setup
        val workout = Workout(id = 2, name = "Morning Routine", date = LocalDate.of(2025, 4, 12))

        //given
        every { workoutService.deleteWorkoutById(2) } returns listOf(workout)

        //when
        mockMvc.perform(delete("/api/workout/delete?id=2"))

        //then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                {
                    "success": true,
                    "info": "Delete Workout by Id",
                    "payload": 
                        [
                        {
                            "id": 2,
                            "name": "Morning Routine",
                            "date":"2025-04-12"
                        }
                        ]
                }
                """
                )
            )
    }
}