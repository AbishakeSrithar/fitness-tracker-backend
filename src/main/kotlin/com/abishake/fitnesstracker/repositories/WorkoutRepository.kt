package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface WorkoutRepository : JpaRepository<Workout, Int> {
    fun findByName(name: String): List<Workout>
    fun findByCreatedAt(date: LocalDate): List<Workout> {
        return findAllByCreatedAtBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay())
    }
    fun findAllByCreatedAtBetween(from: LocalDateTime, to: LocalDateTime): List<Workout>
}