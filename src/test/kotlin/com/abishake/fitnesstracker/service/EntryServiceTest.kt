package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.EntryRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Stream

class EntryServiceTest {
    private val entryRepository: EntryRepository = mockk()
    private val entryService = EntryService(entryRepository)

    @Test
    fun createEntryTest() {
        //setup
        val entryPreSave = Entry(id = null, workoutId = 1, exerciseId = 2, weight = 60.1, sets = 3, reps = 10)
        val entryPostSave = Entry(id = 1, workoutId = 1, exerciseId = 2, weight = 60.1, sets = 3, reps = 10)
        //given
        every { entryRepository.saveAndFlush(entryPreSave) } returns entryPostSave;

        //when
        val result = entryService.createEntry(1, 2, 60.1, 3, 10);

        //then
        assertEquals(entryPostSave, result)
    }

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

    @ParameterizedTest
    @MethodSource("entryIdExistsArgs")
    fun deleteEntryByIdTest(booleans: Boolean, expected: String) {
        //given
        every { entryRepository.findById(2).isPresent } returns booleans;
        every { entryRepository.deleteById(2) } returns Unit;

        //when
        val result = entryService.deleteEntryById(2);

        //then
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        public fun entryIdExistsArgs(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(true, "Successfully deleted Entry with ID: 2"),
                Arguments.of(false, "Entry ID: 2 not found"),
            )
        }
    }
}