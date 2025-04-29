package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface WorkoutRepository : JpaRepository<Workout, Long> {
    fun findByName(name: String): List<Workout>
    fun findByDate(date: LocalDate): List<Workout>
}