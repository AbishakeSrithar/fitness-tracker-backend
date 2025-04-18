package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class ExerciseServiceTest {
    private val exerciseRepository: ExerciseRepository = mockk()
    private val exerciseService = ExerciseService(exerciseRepository)

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
        val result = exerciseService.getAllExercises();

        //then
        assertEquals(exercises, result)
    }

    @Test
    fun getExerciseByIdServiceTest() {
        //setup
        val exercise = Optional.of(Exercise(id = 2, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."))

        //given
        every { exerciseRepository.findById(2) } returns exercise;

        //when
        val result = exerciseService.getExerciseById(2);

        //then
        assertEquals(exercise, result)
    }

    @Test
    fun getExerciseByNameServiceTest() {
        //setup
        val exercise = Optional.of(Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."))

        //given
        every { exerciseRepository.findByName("Squat") } returns exercise;

        //when
        val result = exerciseService.getExerciseByName("Squat");

        //then
        assertEquals(exercise, result)
    }
}