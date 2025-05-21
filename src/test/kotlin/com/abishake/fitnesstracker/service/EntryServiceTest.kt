package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.repositories.EntryRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class EntryServiceTest {
    private val entryRepository: EntryRepository = mockk()
    private val entryService = EntryService(entryRepository)

    @Test
    fun `Create Entry`() {
        //setup
        val entryPreSave = Entry(id = null, workoutId = 1, exerciseId = 2, weight = 60.1, sets = 3, reps = 10)
        val entryPostSave = Entry(id = 1, workoutId = 1, exerciseId = 2, weight = 60.1, sets = 3, reps = 10)
        //given
        every { entryRepository.saveAndFlush(entryPreSave) } returns entryPostSave;

        //when
        val result = entryService.createEntry(1, 2, 60.1, 3, 10);

        //then
        assertEquals(listOf(entryPostSave), result)
    }

    @Test
    fun `Get All Entries`() {
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
    fun `Get Entry by Id`() {
        //setup
        val entry = Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.0, sets = 4, reps = 8)

        //given
        every { entryRepository.findById(2).isPresent } returns true;
        every { entryRepository.findById(2).get() } returns entry;

        //when
        val result = entryService.getEntryById(2);

        //then
        assertEquals(listOf(entry), result)
    }

    @Test
    fun `Get Entries by Workout Id`() {
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
    fun `Get Entries by Exercise Id`() {
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

    @Test
    fun `Update Entry by Id`() {
        //setup
        val entryPreUpdate = Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 60.1, sets = 3, reps = 10)
        val entryPostUpdate = Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 75.1, sets = 5, reps = 12)

        //given
        every { entryRepository.findById(2).isPresent } returns true
        every { entryRepository.findById(2).get() } returns entryPreUpdate
        every { entryRepository.saveAndFlush(entryPostUpdate) } returns entryPostUpdate;

        //when
        val result = entryService.updateEntryById(2, 75.1, 5, 12);

        //then
        assertEquals(listOf(entryPostUpdate), result)
    }

    @Test
    fun `Delete Entry by Id`() {
        //setup
        val entry = Entry(id = 2, workoutId = 1, exerciseId = 2, weight = 75.1, sets = 5, reps = 12)

        //given
        every { entryRepository.findById(2).isPresent } returns true;
        every { entryRepository.findById(2).get() } returns entry;
        every { entryRepository.deleteById(2) } returns Unit;

        //when
        val result = entryService.deleteEntryById(2);

        //then
        assertEquals(listOf(entry), result)
    }
}