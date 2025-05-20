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
    fun `Create Exercise`() {
        //setup
        val exercisePreSave = Exercise(id = null, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
        val exercisePostSave = Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
        //given
        every { exerciseRepository.findByNameAndDescription(name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.") } returns listOf();
        every { exerciseRepository.saveAndFlush(exercisePreSave) } returns exercisePostSave;

        //when
        val result = exerciseService.createExercise("Squat", "A lower body exercise targeting the quads, glutes, and hamstrings.");

        //then
        assertEquals(exercisePostSave, result)
    }

    @Test
    fun `Get All Exercises`() {
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
    fun `Get Exercise by Id`() {
        //setup
        val exercise = Exercise(id = 2, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")

        //given
        every { exerciseRepository.findById(2).isPresent } returns true
        every { exerciseRepository.findById(2).get() } returns exercise

        //when
        val result = exerciseService.getExerciseById(2)

        //then
        assertEquals(exercise, result)
    }

    @Test
    fun `Get Exercise by Name`() {
        //setup
        val exercise = listOf(Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."))

        //given
        every { exerciseRepository.findByName("Squat") } returns exercise;

        //when
        val result = exerciseService.getExerciseByName("Squat");

        //then
        assertEquals(exercise, result)
    }

    @Test
    fun `Update Exercise by Id`() {
        //setup
        val exercisePreUpdate = Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
        val exercisePostUpdate = Exercise(id = 1, name = "Back Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings with bar on your back.")
        //given
        every { exerciseRepository.findById(1).isPresent } returns true
        every { exerciseRepository.findById(1).get() } returns exercisePreUpdate
        every { exerciseRepository.saveAndFlush(exercisePostUpdate) } returns exercisePostUpdate;

        //when
        val result = exerciseService.updateExerciseById(1, "Back Squat", "A lower body exercise targeting the quads, glutes, and hamstrings with bar on your back.");

        //then
        assertEquals(exercisePostUpdate, result)
    }

    @Test
    fun `Delete Exercise by Id`() {
        //setup
        val exercise = Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
        //given
        every { exerciseRepository.findById(2).isPresent } returns true;
        every { exerciseRepository.findById(2).get() } returns exercise;
        every { exerciseRepository.deleteById(2) } returns Unit;

        //when
        val result = exerciseService.deleteExerciseById(2);

        //then
        assertEquals(exercise, result)
    }
}