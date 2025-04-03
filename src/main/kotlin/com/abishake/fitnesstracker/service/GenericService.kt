package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.models.Exercise
import com.abishake.fitnesstracker.models.Workout
import com.abishake.fitnesstracker.repositories.EntryRepository
import com.abishake.fitnesstracker.repositories.ExerciseRepository
import com.abishake.fitnesstracker.repositories.WorkoutRepository
import org.springframework.stereotype.Service

@Service
class GenericService(
//    private val entryRepository: EntryRepository,
    private val workoutRepository: WorkoutRepository,
//    private val exerciseRepository: ExerciseRepository,
) {
//    fun getAllEntries(): List<Entry> {
//        return entryRepository.findAll()
//    }

    fun getAllWorkouts(): List<Workout> {
        return workoutRepository.findAll()
    }

//    fun getAllExercises(): List<Exercise> {
//        return exerciseRepository.findAll()
//    }
}

//@Service
//class GenericService(
//) {
//    fun getAllEntries(): String {
//        return "a"
//    }
//
//    fun getAllWorkouts(): String {
//        return "b"
//    }
//
//    fun getAllExercises(): String {
//        return "c"
//    }
//}