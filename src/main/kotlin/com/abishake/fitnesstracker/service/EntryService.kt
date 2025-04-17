package com.abishake.fitnesstracker.service

import com.abishake.fitnesstracker.models.Entry
import com.abishake.fitnesstracker.repositories.EntryRepository
import org.springframework.stereotype.Service

@Service
class EntryService(
    private val entryRepository: EntryRepository
) {
    // READ
    fun getAllEntries(): List<Entry> {
        return entryRepository.findAll()
    }
}