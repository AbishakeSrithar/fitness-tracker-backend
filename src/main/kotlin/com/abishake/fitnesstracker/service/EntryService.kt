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
    fun createEntry(workoutId: Int, exerciseId: Int, weight: Double, sets: Int, reps: Int): Entry {
        return entryRepository.saveAndFlush(
            Entry(null, workoutId, exerciseId, weight, sets, reps)
        )
    }

    // READ
    fun getAllEntries(): List<Entry> {
        return entryRepository.findAll()
    }

    fun getEntryById(id: Long): Entry {
        if (entryRepository.findById(id).isPresent) {
            return entryRepository.findById(id).get()
        } else {
            throw Exception("Entry Id = $id not found for getEntryById() >> $className")
        }
    }

    fun getEntriesByWorkoutId(workoutId: Int): List<Entry> {
        return entryRepository.findByWorkoutId(workoutId)
    }

    fun getEntriesByExerciseId(exerciseId: Int): List<Entry> {
        return entryRepository.findByExerciseId(exerciseId)
    }

    //  UPDATE
    fun updateEntryById(id: Long, weight: Double, sets: Int, reps: Int): Entry {
        try {
            val entry = getEntryById(id)
            entry.weight = weight
            entry.sets = sets
            entry.reps = reps

            entryRepository.saveAndFlush(entry)
            return entry
        } catch (e: Exception) {
            throw Exception("Exception in updateEntryById() >> $className \n $e", e)
        }
    }

    // DELETE
    fun deleteEntryById(id: Long): Entry {
        val entry = getEntryById(id)
        try {
            entryRepository.deleteById(id)
            return entry
        } catch (e: Exception) {
            throw Exception("Exception in deleteEntryById() >> $className \n $e", e)
        }
    }
}