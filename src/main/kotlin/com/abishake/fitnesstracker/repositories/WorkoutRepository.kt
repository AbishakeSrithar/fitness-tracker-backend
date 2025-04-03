package com.abishake.fitnesstracker.repositories

import com.abishake.fitnesstracker.models.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository : JpaRepository<Workout, Int> {
    fun findByName(name: String): List<Workout>
}