package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

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

    @Test
    fun getWorkoutByIdTest() {
        //setup
        val workout = Optional.of(Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0)))

        //given
        every { workoutsRepository.findById(2) } returns workout;

        //when
        val result = workoutService.getWorkoutById(2);

        //then
        assertEquals(workout, result)
    }

    @Test
    fun getWorkoutByNameTest() {
        //setup
        val workout_1 = Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0))
        val workout_2 = Workout(id = 7, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 22, 13, 30, 0, 0))

        //given
        every { workoutsRepository.findByName("Pull Day") } returns arrayListOf(workout_1, workout_2);

        //when
        val result = workoutService.getWorkoutByName("Pull Day");

        //then
        assertEquals(arrayListOf(workout_1, workout_2), result)
    }

    @Test
    fun getWorkoutByDateTest() {
        //setup
        val workout_2 = Workout(id = 7, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 22, 13, 30, 0, 0))

        //given
        every { workoutsRepository.findByCreatedAt(LocalDate.of(2025, 4, 22)) } returns arrayListOf(workout_2);

        //when
        val result = workoutService.getWorkoutByDate(LocalDate.of(2025, 4, 22));

        //then
        assertEquals(arrayListOf(workout_2), result)
    }
}