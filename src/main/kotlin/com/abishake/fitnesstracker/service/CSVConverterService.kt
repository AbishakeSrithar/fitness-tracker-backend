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

    val createdWorkoutDates = mutableListOf<String>()
    val createdExerciseNames = mutableListOf<String>()

    fun readCSV() {
        var i = 0

        val inputStream = this::class.java.classLoader.getResourceAsStream("strongData.csv")
            ?: throw IllegalArgumentException("File not found: strongData.csv")

        csvReader {
            delimiter = ';'
        }.open(inputStream) {
            readAllWithHeaderAsSequence().forEach { row ->
                println(i)
                i++
                if (row["Set Order"] != "Note" && row["Set Order"] != "Rest Timer") {
                    println("Workout #: ${row["Workout #"]}, Exercise Name: ${row["Exercise Name"]}, Weight (kg): ${row["Weight (kg)"]}, Reps: ${row["Reps"]}")
                    println("Date: ${row["Date"]}")

                    createAllWorkouts(row)
                    createAllExercises(row)
                    createAllEntries(row)


                }
            }
        }
    }

    fun createAllWorkouts(row:  Map<String, String>) {
        // Very reliant on 1 workout per day - will break otherwise
        val date = row["Date"]?.slice(0..9)
        val year = Integer.parseInt(date?.slice(0..3))
        val month = Integer.parseInt(date?.slice(5..6))
        val day = Integer.parseInt(date?.slice(8..9))
        val workoutName = row["Workout Name"]
        if (date != null && workoutName != null && date in createdWorkoutDates) {
            println("Already created Workout for: ${row["Date"]}")
        } else if (date != null  && workoutName != null && date !in createdWorkoutDates) {
            // If workout by date does not exist, create
            workoutService.createWorkout(workoutName, LocalDate.of(year, month, day))
            // add to cache (can hopefully use this to skip gets/creates too much
            createdWorkoutDates.add(date)
            println("Creating Workout for: ${row["Date"]}")
        } else {
            println("Can't parse this Date: ${row["Date"]}")
        }
    }

    fun createAllExercises(row:  Map<String, String>) {
        // Very reliant on 1 workout per day - will break otherwise
        val exercise = row["Exercise Name"]
        if (exercise != null && exercise in createdExerciseNames) {
            println("Already created Exercise for: ${row["Exercise Name"]}")
        } else if (exercise != null && exercise !in createdExerciseNames) {
            // If workout by date does not exist, create
            exerciseService.createExercise(exercise, exercise)
            // add to cache (can hopefully use this to skip gets/creates too much
            createdExerciseNames.add(exercise)
            println("Creating Exercise for: ${row["Exercise Name"]}")
        } else {
            println("Can't parse this Exercise: ${row["Exercise Name"]}")
        }
    }

    fun createAllEntries(row:  Map<String, String>) {
        // Very reliant on 1 workout per day - will break otherwise
        val exercise = row["Exercise Name"]
        val date = row["Date"]?.slice(0..9)
        println(row["Weight (kg)"])
        val weight = row["Weight (kg)"]
        val reps = row["Reps"]
        val seconds = row["Seconds"]
        if (exercise != null && date != null && weight != null && reps != null && seconds != null) {
            println("inside here")
            // should probs cache id instead of fetching everytime
            // HEAVILY ASSUMING THERE'S ONLY 1 MATCH
            val workoutId = workoutService.getWorkoutsByDate(LocalDate.parse(date))[0].id?.toInt()
            val exerciseId = exerciseService.getExerciseByName(exercise)[0].id?.toInt()
            var weightAsDouble: Double = 0.0

            if (weight != "") {
                weightAsDouble = weight.toDouble()
            }
            var repsAsInt: Int = 0

            repsAsInt = if (reps != "") {
                reps.toInt()
            } else if (seconds != "") {
                println("seconds: $seconds")
                seconds.toDouble().toInt()
            } else {
                0
            }

            println(workoutId)
            println(exerciseId)

            if (workoutId != null && exerciseId != null) {
                println("createEntry")
                entryService.createEntry(workoutId, exerciseId, weightAsDouble, 1, repsAsInt)
            }


        }
    }
}