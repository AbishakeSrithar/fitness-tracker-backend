package com.abishake.fitnesstracker.fitness_tracker.repositories

import com.abishake.fitnesstracker.fitness_tracker.models.Entry
import org.springframework.data.jpa.repository.JpaRepository

interface EntryRepository : JpaRepository<Entry, Int>