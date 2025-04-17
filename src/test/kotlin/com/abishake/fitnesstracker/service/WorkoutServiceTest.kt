package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDateTime

class WorkoutServiceTest {
    private val workoutsRepository: WorkoutRepository = mockk()
    private val workoutService = WorkoutService(workoutsRepository)

    @Test
    fun getAllWorkoutsTest() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", createdAt = LocalDateTime.of(2025, 4, 14, 13, 0, 0, 0)),
            Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0)),
            Workout(id = 3, name = "Leg Day", createdAt = LocalDateTime.of(2025, 4, 16, 13, 0, 0, 0))
        )
        //given
        every { workoutsRepository.findAll() } returns workouts;

        //when
        val result = workoutService.getAllWorkouts();

        //then
        assertEquals(workouts, result)
    }

//    @Test
//    fun getWorkoutByIdTest() {
//        //setup
//        val workouts = listOf(
//            Workout(id = 1, name = "Push Day", createdAt = LocalDateTime.of(2025, 4, 14, 13, 0, 0, 0)),
//            Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0)),
//            Workout(id = 3, name = "Leg Day", createdAt = LocalDateTime.of(2025, 4, 16, 13, 0, 0, 0))
//        )
//        //given
//        every { workoutsRepository.findById(2) } returns Optional.of(workouts[1]);
//
//        //when
//        val result = mainService.getWorkoutById(2);
//
//        //then
//        assertEquals(workouts, result)
//    }
}