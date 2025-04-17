package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

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
}