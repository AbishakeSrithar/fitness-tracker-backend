//package com.abishake.fitnesstracker.controller
//
//import com.abishake.fitnesstracker.controllers.WorkoutController
//import com.abishake.fitnesstracker.models.RestResponse
//import com.abishake.fitnesstracker.models.Workout
//import com.abishake.fitnesstracker.service.WorkoutService
//import com.ninjasquad.springmockk.MockkBean
//import io.mockk.every
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
//import java.time.LocalDate
//import java.util.Optional
//
//@WebMvcTest(WorkoutController::class)
//class WorkoutControllerTest(
//    @Autowired val mockMvc: MockMvc
//) {
//
//    @MockkBean
//    lateinit var workoutService: WorkoutService
//    // CREATE
//    @Test
//    fun `Create Workout`() {
//        val date = LocalDate.now()
//        val workout = Workout(1, name = "Push Day", date = date)
//
//        every { workoutService.createWorkout("Push Day", date) } returns workout
//
//        mockMvc.perform(post("/api/workout/create?name=Push Day"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":1, "name":"Push Day", "date": "$date"}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get All Workouts`() {
//        val workouts = listOf(
//            Workout(id = 1, name = "Push Day", date = LocalDate.of(2025, 4, 14)),
//            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15))
//        )
//
//        every { workoutService.getAllWorkouts() } returns workouts
//
//        mockMvc.perform(get("/api/workout/get"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                """
//                [{"id":1,"name":"Push Day","date":"2025-04-14"},
//                {"id":2,"name":"Pull Day","date":"2025-04-15"}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Workout by Id`() {
//        val workout = Optional.of(Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15)))
//
//        every { workoutService.getWorkoutById(2) } returns workout
//
//        mockMvc.perform(get("/api/workout/id?id=2"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"id":2,"name":"Pull Day","date":"2025-04-15"}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Workouts by Name`() {
//        val workouts = listOf(
//            Workout(id = 1, name = "Pull Day", date = LocalDate.of(2025, 4, 14)),
//            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15))
//        )
//
//        every { workoutService.getWorkoutByName("Pull Day") } returns workouts
//
//        mockMvc.perform(get("/api/workout/name?name=Pull Day"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                [{"id":1,"name":"Pull Day","date":"2025-04-14"},
//                {"id":2,"name":"Pull Day","date":"2025-04-15"}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Get Workouts by Date`() {
//        val workouts = listOf(
//            Workout(id = 1, name = "Push Day", date = LocalDate.of(2025, 4, 14)),
//            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 14))
//        )
//
//        every { workoutService.getWorkoutByDate(LocalDate.of(2025, 4, 14)) } returns workouts
//
//        mockMvc.perform(get("/api/workout/date?date=14/04/2025"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                [{"id":1,"name":"Push Day","date":"2025-04-14"},
//                {"id":2,"name":"Pull Day","date":"2025-04-14"}]
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Update Workout by Id`() {
//
//        every { workoutService.updateWorkoutById(2, "Morning Routine", LocalDate.of(2025, 4, 12)) } returns RestResponse("True", "Successfully updated Workout with ID: 2 to have name=Morning Routine, date=2025-04-12")
//
//        mockMvc.perform(put("/api/workout/update?id=2&name=Morning Routine&date=2025-04-12"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"isOk": "True", "reason": "Successfully updated Workout with ID: 2 to have name=Morning Routine, date=2025-04-12"}
//                """
//                )
//            )
//    }
//
//    @Test
//    fun `Delete Workout By Id`() {
//
//        every { workoutService.deleteWorkoutById(2) } returns RestResponse("True", "Successfully deleted Workout with ID: 2")
//
//        mockMvc.perform(delete("/api/workout/delete?id=2"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(
//                content().json(
//                    """
//                {"isOk": "True", "reason": "Successfully deleted Workout with ID: 2"}
//                """
//                )
//            )
//    }
//}