//package com.abishake.fitnesstracker.service
//
//import com.abishake.fitnesstracker.models.Exercise
//import com.abishake.fitnesstracker.models.RestResponse
//import com.abishake.fitnesstracker.repositories.ExerciseRepository
//import io.mockk.every
//import io.mockk.mockk
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.params.ParameterizedTest
//import org.junit.jupiter.params.provider.Arguments
//import org.junit.jupiter.params.provider.MethodSource
//import java.util.*
//import java.util.stream.Stream
//
//class ExerciseServiceTest {
//    private val exerciseRepository: ExerciseRepository = mockk()
//    private val exerciseService = ExerciseService(exerciseRepository)
//
//    @Test
//    fun `Create Exercise`() {
//        //setup
//        val exercisePreSave = Exercise(id = null, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
//        val exercisePostSave = Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
//        //given
//        every { exerciseRepository.saveAndFlush(exercisePreSave) } returns exercisePostSave;
//
//        //when
//        val result = exerciseService.createExercise("Squat", "A lower body exercise targeting the quads, glutes, and hamstrings.");
//
//        //then
//        assertEquals(exercisePostSave, result)
//    }
//
//    @Test
//    fun `Get All Exercises`() {
//        //setup
//        val exercises = listOf(
//            Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."),
//            Exercise(id = 2, name = "Bench Press", description = "A chest exercise that targets the pectorals, shoulders, and triceps.")
//        )
//        //given
//        every { exerciseRepository.findAll() } returns exercises;
//
//        //when
//        val result = exerciseService.getAllExercises();
//
//        //then
//        assertEquals(exercises, result)
//    }
//
//    @Test
//    fun `Get Exercise by Id`() {
//        //setup
//        val exercise = Optional.of(Exercise(id = 2, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."))
//
//        //given
//        every { exerciseRepository.findById(2) } returns exercise;
//
//        //when
//        val result = exerciseService.getExerciseById(2);
//
//        //then
//        assertEquals(exercise, result)
//    }
//
//    @Test
//    fun `Get Exercise by Name`() {
//        //setup
//        val exercise = Optional.of(Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings."))
//
//        //given
//        every { exerciseRepository.findByName("Squat") } returns exercise;
//
//        //when
//        val result = exerciseService.getExerciseByName("Squat");
//
//        //then
//        assertEquals(exercise, result)
//    }
//
//    @Test
//    fun `Update Exercise by Id`() {
//        //setup
//        val exercisePreUpdate = Exercise(id = 1, name = "Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings.")
//        val exercisePostUpdate = Exercise(id = 1, name = "Back Squat", description = "A lower body exercise targeting the quads, glutes, and hamstrings with bar on your back.")
//        val expectedRes = RestResponse("True", "Successfully updated Exercise with ID: 1 to have name=Back Squat, description=A lower body exercise targeting the quads, glutes, and hamstrings with bar on your back.")
//
//        //given
//        every { exerciseRepository.findById(1) } returns Optional.of(exercisePreUpdate);
//        every { exerciseRepository.saveAndFlush(exercisePostUpdate) } returns exercisePostUpdate;
//
//        //when
//        val result = exerciseService.updateExerciseById(1, "Back Squat", "A lower body exercise targeting the quads, glutes, and hamstrings with bar on your back.");
//
//        //then
//        assertEquals(expectedRes, result)
//    }
//
//    @ParameterizedTest
//    @MethodSource("exerciseIdExistsArgs")
//    fun `Delete Exercise by Id`(booleans: Boolean, expected: RestResponse) {
//        //given
//        every { exerciseRepository.findById(2).isPresent } returns booleans;
//        every { exerciseRepository.deleteById(2) } returns Unit;
//
//        //when
//        val result = exerciseService.deleteExerciseById(2);
//
//        //then
//        assertEquals(expected, result)
//    }
//
//    companion object {
//        @JvmStatic
//        fun exerciseIdExistsArgs(): Stream<Arguments> {
//            return Stream.of(
//                Arguments.of(true, RestResponse("True", "Successfully deleted Exercise with ID: 2")),
//                Arguments.of(false, RestResponse("False", "Exercise ID: 2 not found")),
//            )
//        }
//    }
//}