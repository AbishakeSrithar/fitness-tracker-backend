package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.repositories.EntryRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class EntryService(
    private val entryRepository: EntryRepository
) {
    // READ
    fun getAllEntries(): List<Entry> {
        return entryRepository.findAll()
    }

    fun getEntryById(id: Int): Optional<Entry> {
        return entryRepository.findById(id)
    }

    fun getEntriesByWorkoutId(workoutId: Int): List<Entry> {
        return entryRepository.findByWorkoutId(workoutId)
    }

    fun getEntriesByExerciseId(exerciseId: Int): List<Entry> {
        return entryRepository.findByExerciseId(exerciseId)
    }
}