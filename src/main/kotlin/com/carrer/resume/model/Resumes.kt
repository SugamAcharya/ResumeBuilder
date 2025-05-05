package com.carrer.resume.model

import com.carrer.resume.entity.ResumeEntity

data class Resumes(val resume: List<Resume>)

data class Resume(
    val id: Int,
    val name: String,
    val address: String,
    val contact: String,
    val professionalSummary: String,
    val skills: List<String>,
    /*val experience: List<Experience>,
    val education: List<Education>,
    val achievements: List<Achievement>*/
) {
}

data class NewResume(
    val name: String,
    val address: String,
    val contact: String,
    val professionalSummary: String,
    val skills: List<String>,
    /*val experience: List<Experience>,
    val education: List<Education>,
    val achievements: List<Achievement>*/
)




