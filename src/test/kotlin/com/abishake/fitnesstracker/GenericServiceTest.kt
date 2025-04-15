package com.abishake.fitnesstracker

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.EntryRepository
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import com.abishake.fitnesstracker.service.GenericService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDateTime

class GenericServiceTest {
    private val entryRepository: EntryRepository = mockk()
    private val workoutsRepository: WorkoutRepository = mockk()
    private val exerciseRepository: ExerciseRepository = mockk()
    private val genericService = GenericService(entryRepository, workoutsRepository, exerciseRepository)

    @Test
    fun findAllEntries() {
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
        val result = genericService.getAllEntries();

        //then
        assertEquals(entries, result)
    }

    @Test
    fun findAllWorkouts() {
        //setup
        val workouts = listOf(
            Workout(id = 1, name = "Push Day", createdAt = LocalDateTime.of(2025, 4, 14, 13, 0, 0, 0)),
            Workout(id = 2, name = "Pull Day", createdAt = LocalDateTime.of(2025, 4, 15, 13, 0, 0, 0)),
            Workout(id = 3, name = "Leg Day", createdAt = LocalDateTime.of(2025, 4, 16, 13, 0, 0, 0))
        )
        //given
        every { workoutsRepository.findAll() } returns workouts;

        //when
        val result = genericService.getAllWorkouts();

        //then
        assertEquals(workouts, result)
    }

    @Test
    fun findAllExercises() {
        //setup
        val exercises = listOf(
            Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."),
            Exercise(id = 2, name = "Bench Press", description = "A chest exercise that targets the pectorals, shoulders, and triceps.")
        )
        //given
        every { exerciseRepository.findAll() } returns exercises;

        //when
        val result = genericService.getAllExercises();

        //then
        assertEquals(exercises, result)
    }
}