package com.abishake.fitnesstracker.models

data class RestResponse<T>(
    val success: Boolean,
    val info: String,
    val payload: T
)