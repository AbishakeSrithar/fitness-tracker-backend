package com.abishake.fitnesstracker.models

import jakarta.persistence.*

@Entity
@Table(name = "exercises")
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    val name: String,

    @Column(name = "desc")
    val description: String
)