package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate


class WorkoutServiceTest {
    private val workoutsRepository: WorkoutRepository = mockk()
    private val workoutService = WorkoutService(workoutsRepository)

    @Test
    fun `Create Workout`() {
        //setup
        val date = LocalDate.of(2025, 4, 14)
        val workoutPreSave = Workout(id = null, name = "Pull Day", date = date)
        val workoutPostSave = Workout(id = 2, name = "Pull Day", date = date)
        //given
        every { workoutsRepository.saveAndFlush(workoutPreSave) } returns workoutPostSave

        //when
        val result = workoutService.createWorkout("Pull Day", date)

        //then
        assertEquals(workoutPostSave, result)
    }

    @Test
    fun `Get All Workouts`() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", date = LocalDate.of(2025, 4, 14)),
            Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 15)),
            Workout(id = 3, name = "Leg Day", date = LocalDate.of(2025, 4, 16))
        )

        //given
        every { workoutsRepository.findAll() } returns workouts

        //when
        val result = workoutService.getAllWorkouts()

        //then
        assertEquals(workouts, result)
    }

    @Test
    fun `Get Workout by Id`() {
        //setup
        val workout = Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 14))

        //given
        every { workoutsRepository.findById(2).isPresent } returns true
        every { workoutsRepository.findById(2).get() } returns workout

        //when
        val result = workoutService.getWorkoutById(2)

        //then
        assertEquals(workout, result)
    }

    @Test
    fun `Get Workouts by Name`() {
        //setup
        val workout1 = Workout(id = 2, name = "Pull Day", date = LocalDate.of(2025, 4, 14))
        val workout2 = Workout(id = 7, name = "Pull Day", date = LocalDate.of(2025, 4, 22))

        //given
        every { workoutsRepository.findByName("Pull Day") } returns arrayListOf(workout1, workout2)

        //when
        val result = workoutService.getWorkoutsByName("Pull Day")

        //then
        assertEquals(arrayListOf(workout1, workout2), result)
    }

    @Test
    fun `Get Workouts by Date`() {
        //setup
        val workout2 = Workout(id = 7, name = "Pull Day", date = LocalDate.of(2025, 4, 22))

        //given
        every { workoutsRepository.findByDate(LocalDate.of(2025, 4, 22)) } returns arrayListOf(workout2)

        //when
        val result = workoutService.getWorkoutsByDate(LocalDate.of(2025, 4, 22))

        //then
        assertEquals(arrayListOf(workout2), result)
    }

    @Test
    fun `Update Workout by Id`() {
        //setup
        val datePreUpdate = LocalDate.of(2025, 4, 14)
        val datePostUpdate = LocalDate.of(2024, 5, 22)
        val workoutPreUpdate = Workout(id = 2, name = "Pull Day", date = datePreUpdate)
        val workoutPostUpdate = Workout(id = 2, name = "Pull + Push Day", date = datePostUpdate)

        //given
        every { workoutsRepository.findById(2).isPresent } returns true
        every { workoutsRepository.findById(2).get() } returns workoutPreUpdate
        every { workoutsRepository.saveAndFlush(workoutPostUpdate) } returns workoutPostUpdate;

        //when
        val result = workoutService.updateWorkoutById(2, "Pull + Push Day", datePostUpdate);

        //then
        assertEquals(workoutPostUpdate, result)
    }

    @Test
    fun `Delete Workout by Id`() {
        //setup
        val date = LocalDate.of(2024, 5, 22)
        val workout = Workout(id = 2, name = "Pull + Push Day", date = date)
        //given
        every { workoutsRepository.findById(2).isPresent } returns true
        every { workoutsRepository.findById(2).get() } returns workout
        every { workoutsRepository.deleteById(2) } returns Unit

        //when
        val result = workoutService.deleteWorkoutById(2)

        //then
        assertEquals(Unit, result)
    }
}