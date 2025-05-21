package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.repositories.EntryRepository
import org.springframework.stereotype.Service

@Service
class EntryService(
    private val entryRepository: EntryRepository
) {

    private val className = EntryService::class

    // CREATE
    fun createEntry(workoutId: Int, exerciseId: Int, weight: Double, sets: Int, reps: Int): List<Entry> {
        return listOf(entryRepository.saveAndFlush(
            Entry(null, workoutId, exerciseId, weight, sets, reps)
        ))
    }

    // READ
    fun getAllEntries(): List<Entry> {
        return entryRepository.findAll()
    }

    fun getEntryById(id: Long): List<Entry> {
        return if (entryRepository.findById(id).isPresent) {
            listOf(entryRepository.findById(id).get())
        } else {
            listOf()
        }
    }

    fun getEntriesByWorkoutId(workoutId: Int): List<Entry> {
        return entryRepository.findByWorkoutId(workoutId)
    }

    fun getEntriesByExerciseId(exerciseId: Int): List<Entry> {
        return entryRepository.findByExerciseId(exerciseId)
    }

    //  UPDATE
    fun updateEntryById(id: Long, weight: Double, sets: Int, reps: Int): List<Entry>  {
        val entryInList = getEntryById(id)
        val entry = entryInList.first()
        entry.weight = weight
        entry.sets = sets
        entry.reps = reps

        val saved = entryRepository.saveAndFlush(entry)
        return listOf(saved)
    }

    // DELETE
    fun deleteEntryById(id: Long): List<Entry>  {
        val entry = getEntryById(id)
        if (entry.isNotEmpty()) {
            entryRepository.deleteById(id)
        }
        return entry
    }
}