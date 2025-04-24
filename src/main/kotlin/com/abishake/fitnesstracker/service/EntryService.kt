package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.RestResponse
import com.abishake.fitnesstracker.repositories.EntryRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class EntryService(
    private val entryRepository: EntryRepository
) {
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

    fun getEntryById(id: Long): Optional<Entry> {
        return entryRepository.findById(id)
    }

    fun getEntriesByWorkoutId(workoutId: Int): List<Entry> {
        return entryRepository.findByWorkoutId(workoutId)
    }

    fun getEntriesByExerciseId(exerciseId: Int): List<Entry> {
        return entryRepository.findByExerciseId(exerciseId)
    }

    //  UPDATE
    fun updateEntryById(id: Long, weight: Double, sets: Int, reps: Int): RestResponse {
        // Find the Entry if it exists (return can't find if not)
        if (getEntryById(id).isPresent) {
            val entry = getEntryById(id).get()
            entry.weight = weight
            entry.sets = sets
            entry.reps = reps

            try {
                entryRepository.saveAndFlush(entry)
                return RestResponse("True", "Successfully updated Entry with ID: $id to have weight=$weight, sets=$sets, reps=$reps")
            } catch (e: Exception) {
                return RestResponse("False", "Error while updating Entry with ID: $id")
            }
        } else {
            return RestResponse("False", "Cannot find Entry with ID: $id to update")
        }
    }

    // DELETE
    fun deleteEntryById(id: Long): RestResponse {
        if (entryRepository.findById(id).isPresent) {
            entryRepository.deleteById(id)
            return RestResponse("True", "Successfully deleted Entry with ID: $id")
        } else {
            return RestResponse("False", "Entry ID: $id not found")
        }
    }
}