package com.carrer.resume.entity

import com.carrer.resume.model.Experience
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "TBL_EXPERIENCE")
data class ExperienceEntity(
    val companyName: String,
    val startDate: String,
    val endDate: String,
    val techUsed: String,
    val aboutProject: String,
    val contribution: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}

fun ExperienceEntity.toExperience(): Experience = Experience(
    this.id!!, this.companyName, this.startDate, this.endDate,
    this.techUsed, this.aboutProject, this.contribution
)
