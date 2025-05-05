package com.carrer.resume.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity(name = "TBL_EDUCATION")
data class EducationEntity(
    val name: String,
    val address: String,
    val startedYear: LocalDate,
    val endYear: LocalDate,
    val marks: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}
