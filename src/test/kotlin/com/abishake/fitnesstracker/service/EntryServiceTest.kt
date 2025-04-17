package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.EntryRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDateTime

class EntryServiceTest {
    private val entryRepository: EntryRepository = mockk()
    private val entryService = EntryService(entryRepository)

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
        val result = entryService.getAllEntries();

        //then
        assertEquals(entries, result)
    }
}