package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.*
import java.util.stream.Stream


class WorkoutServiceTest {
    private val workoutsRepository: WorkoutRepository = mockk()
    private val workoutService = WorkoutService(workoutsRepository)

    @Test
    fun createWorkoutTest() {
        //setup
        val date = LocalDate.of(2025, 4, 14)
        val workoutPreSave = Workout(id = null, name = "Pull Day", createdAt = date)
        val workoutPostSave = Workout(id = 2, name = "Pull Day", createdAt = date)
        //given
        every { workoutsRepository.saveAndFlush(workoutPreSave) } returns workoutPostSave

        //when
        val result = workoutService.createWorkout("Pull Day", date)

        //then
        assertEquals(workoutPostSave, result)
    }

    @Test
    fun getAllWorkoutsServiceTest() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", createdAt = LocalDate.of(2025, 4, 14)),
            Workout(id = 2, name = "Pull Day", createdAt = LocalDate.of(2025, 4, 15)),
            Workout(id = 3, name = "Leg Day", createdAt = LocalDate.of(2025, 4, 16))
        )

        //given
        every { workoutsRepository.findAll() } returns workouts

        //when
        val result = workoutService.getAllWorkouts()

        //then
        assertEquals(workouts, result)
    }

    @Test
    fun getWorkoutByIdServiceTest() {
        //setup
        val workout = Optional.of(Workout(id = 2, name = "Pull Day", createdAt = LocalDate.of(2025, 4, 14)))

        //given
        every { workoutsRepository.findById(2) } returns workout

        //when
        val result = workoutService.getWorkoutById(2)

        //then
        assertEquals(workout, result)
    }

    @Test
    fun getWorkoutByNameServiceTest() {
        //setup
        val workout1 = Workout(id = 2, name = "Pull Day", createdAt = LocalDate.of(2025, 4, 14))
        val workout2 = Workout(id = 7, name = "Pull Day", createdAt = LocalDate.of(2025, 4, 22))

        //given
        every { workoutsRepository.findByName("Pull Day") } returns arrayListOf(workout1, workout2)

        //when
        val result = workoutService.getWorkoutByName("Pull Day")

        //then
        assertEquals(arrayListOf(workout1, workout2), result)
    }

    @Test
    fun getWorkoutByDateServiceTest() {
        //setup
        val workout2 = Workout(id = 7, name = "Pull Day", createdAt = LocalDate.of(2025, 4, 22))

        //given
        every { workoutsRepository.findByCreatedAt(LocalDate.of(2025, 4, 22)) } returns arrayListOf(workout2)

        //when
        val result = workoutService.getWorkoutByDate(LocalDate.of(2025, 4, 22))

        //then
        assertEquals(arrayListOf(workout2), result)
    }

    @Test
    fun updateWorkoutByIdTest() {
        //setup
        val datePreUpdate = LocalDate.of(2025, 4, 14)
        val datePostUpdate = LocalDate.of(2024, 5, 22)
        val workoutPreUpdate = Workout(id = 2, name = "Pull Day", createdAt = datePreUpdate)
        val workoutPostUpdate = Workout(id = 2, name = "Pull + Push Day", createdAt = datePostUpdate)
        val expectedRes = RestResponse("True", "Successfully updated Workout with ID: 2 to have name=Pull + Push Day, createdAt=2024-05-22")

        //given
        every { workoutsRepository.findById(2) } returns Optional.of(workoutPreUpdate);
        every { workoutsRepository.saveAndFlush(workoutPostUpdate) } returns workoutPostUpdate;

        //when
        val result = workoutService.updateWorkoutById(2, "Pull + Push Day", datePostUpdate);

        //then
        assertEquals(expectedRes, result)
    }

    @ParameterizedTest
    @MethodSource("workoutIdExistsArgs")
    fun deleteWorkoutByIdTest(booleans: Boolean, expected: RestResponse) {
        //given
        every { workoutsRepository.findById(2).isPresent } returns booleans
        every { workoutsRepository.deleteById(2) } returns Unit

        //when
        val result = workoutService.deleteWorkoutById(2)

        //then
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun workoutIdExistsArgs(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(true, RestResponse("True", "Successfully deleted Workout with ID: 2")),
                Arguments.of(false, RestResponse("False", "Workout ID: 2 not found")),
            )
        }
    }
}