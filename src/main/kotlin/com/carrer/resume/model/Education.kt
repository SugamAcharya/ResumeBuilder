package com.carrer.resume.model

import java.time.LocalDate

data class Education(
    val name: String,
    val address: String,
    val startedYear: LocalDate,
    val endYear: LocalDate,
    val marks: String
)
