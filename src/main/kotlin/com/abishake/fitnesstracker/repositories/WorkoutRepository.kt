package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface WorkoutRepository : JpaRepository<Workout, Int> {
    fun findByName(name: String): List<Workout>
    fun findByCreatedAt(date: LocalDate): List<Workout>
}