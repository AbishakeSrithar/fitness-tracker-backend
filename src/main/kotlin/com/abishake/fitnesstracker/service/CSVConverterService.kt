package com.abishake.fitnesstracker.service

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CSVConverterService (
    private val workoutService: WorkoutService,
    private val exerciseService: ExerciseService,
    private val entryService: EntryService
) {

    val createdWorkoutDates = mutableMapOf<String, Long>()
    val createdExerciseNames = mutableMapOf<String, Long>()

    fun readCSV() {
        var i = 0

        val inputStream = this::class.java.classLoader.getResourceAsStream("strongData.csv")
            ?: throw IllegalArgumentException("File not found: strongData.csv")

        csvReader {
            delimiter = ';'
        }.open(inputStream) {
            readAllWithHeaderAsSequence().forEach { row ->
                if (row["Set Order"] != "Note" && row["Set Order"] != "Rest Timer") { // Specific for my data
                    createAllWorkouts(row)
                    createAllExercises(row)
                    createAllEntries(row)
                }
            }
        }
    }

    fun createAllWorkouts(row:  Map<String, String>) {
        val date = row["Date"]?.slice(0..9)
        val workoutName = row["Workout Name"]
        if (date != null  && workoutName != null && date !in createdWorkoutDates.keys) {
            val payload = workoutService.createWorkout(workoutName, LocalDate.parse(date))
            payload[0].id?.let { createdWorkoutDates.put(workoutName, it) }
        }
    }

    fun createAllExercises(row:  Map<String, String>) {
        val exerciseName = row["Exercise Name"]
        if (exerciseName != null && exerciseName !in createdExerciseNames.keys) {
            val payload = exerciseService.createExercise(exerciseName, exerciseName)
            payload[0].id?.let { createdExerciseNames.put(exerciseName, it) }
        }
    }

    fun createAllEntries(row:  Map<String, String>) {
        val exercise = row["Exercise Name"]
        val date = row["Date"]?.slice(0..9)
        val weight = row["Weight (kg)"]
        val reps = row["Reps"]
        val seconds = row["Seconds"]
        if (exercise != null && date != null && weight != null && reps != null && seconds != null) {
            val workoutId = workoutService.getWorkoutsByDate(LocalDate.parse(date))[0].id?.toInt()
            val exerciseId = exerciseService.getExerciseByName(exercise)[0].id?.toInt()
            val weightAsDouble = weight.toDoubleOrNull() ?: 0.0
            val repsOrSecs = when {
                reps.isNotBlank() -> reps.toIntOrNull() ?: 0
                seconds.isNotBlank() -> seconds.toDoubleOrNull()?.toInt() ?: 0
                else -> 0
            }

            if (workoutId != null && exerciseId != null) {
                entryService.createEntry(workoutId, exerciseId, weightAsDouble, 1, repsOrSecs)
            }
        }
    }
}