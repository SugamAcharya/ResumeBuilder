package com.carrer.resume.entity


import com.carrer.resume.model.NewResume
import com.carrer.resume.model.Resume
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "TBL_RESUME")
data class ResumeEntity(
    val name: String,
    val address: String,
    val contact: String,
    val professionalSummary: String,
    val skills: List<String>,
    /* val experience: List<ExperienceEntity>,
     val education: List<EducationEntity>,
     val achievements: List<AchievementEntity>*/

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}

fun ResumeEntity.toResume(): Resume = Resume(
    this.id!!, this.name, this.address, this.contact, this.professionalSummary,
    this.skills
)

fun NewResume.toResumeEntity(): ResumeEntity = ResumeEntity(
    this.name, this.address, this.contact, this.professionalSummary, this.skills
)