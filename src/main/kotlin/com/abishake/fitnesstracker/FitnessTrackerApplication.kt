package com.abishake.fitnesstracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FitnessTrackerApplication

fun main(args: Array<String>) {
	runApplication<FitnessTrackerApplication>(*args)
}
