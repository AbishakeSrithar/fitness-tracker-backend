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
import java.util.*

class EntryServiceTest {
    private val entryRepository: EntryRepository = mockk()
    private val entryService = EntryService(entryRepository)

    @Test
    fun getAllEntriesTest() {
        //setup
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )
        //given
        every { entryRepository.findAll() } returns entries;

        //when
        val result = entryService.getAllEntries();

        //then
        assertEquals(entries, result)
    }

    @Test
    fun getEntryByIdServiceTest() {
        //setup
        val entry = Optional.of(Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8))

        //given
        every { entryRepository.findById(2) } returns entry;

        //when
        val result = entryService.getEntryById(2);

        //then
        assertEquals(entry, result)
    }

    @Test
    fun getEntriesByWorkoutIdServiceTest() {
        //setup
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 1, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )
        //given
        every { entryRepository.findByWorkoutId(1) } returns entries;

        //when
        val result = entryService.getEntriesByWorkoutId(1);

        //then
        assertEquals(entries, result)
    }

    @Test
    fun getEntriesByExerciseIdServiceTest() {
        //setup
        val entries = listOf(
            Entry(id = 1, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 3, reps = 10),
            Entry(id = 2, workoutId = 2, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)
        )
        //given
        every { entryRepository.findByExerciseId(2) } returns entries;

        //when
        val result = entryService.getEntriesByExerciseId(2);

        //then
        assertEquals(entries, result)
    }
}