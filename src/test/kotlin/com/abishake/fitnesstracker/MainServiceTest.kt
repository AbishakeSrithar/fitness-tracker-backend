package com.abishake.fitnesstracker

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.EntryRepository
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import com.abishake.fitnesstracker.service.MainService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDateTime
import java.util.*

class MainServiceTest {
    private val entryRepository: EntryRepository = mockk()
    private val workoutsRepository: WorkoutRepository = mockk()
    private val exerciseRepository: ExerciseRepository = mockk()
    private val mainService = MainService(entryRepository, workoutsRepository, exerciseRepository)

    @Test
    fun getAllEntriesTest() {
        //setup
        val workout_full_body = Workout(id = 1, name = "Full Body Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0))
        val exercise_squat = Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
        val exercise_bench = Exercise(id = 2, name = "Bench Press", description = "A chest exercise that targets the pectorals, shoulders, and triceps.")
        val entries = listOf(
            Entry(workout = workout_full_body, exercise = exercise_squat, weight = 60.0, sets = 3, reps = 10),
            Entry(workout = workout_full_body, exercise = exercise_bench, weight = 60.0, sets = 4, reps = 8)
        )
        //given
        every { entryRepository.findAll() } returns entries;

        //when
        val result = mainService.getAllEntries();

        //then
        assertEquals(entries, result)
    }

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
        val result = mainService.getAllWorkouts();

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

    @Test
    fun getAllExercisesTest() {
        //setup
        val exercises = listOf(
            Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."),
            Exercise(id = 2, name = "Bench Press", description = "A chest exercise that targets the pectorals, shoulders, and triceps.")
        )
        //given
        every { exerciseRepository.findAll() } returns exercises;

        //when
        val result = mainService.getAllExercises();

        //then
        assertEquals(exercises, result)
    }
}