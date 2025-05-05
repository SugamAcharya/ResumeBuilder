package com.carrer.resume.service

import com.carrer.resume.entity.ResumeEntity
import com.carrer.resume.entity.toResume
import com.carrer.resume.entity.toResumeEntity
import com.carrer.resume.model.NewResume
import com.carrer.resume.model.Resume
import com.carrer.resume.repository.ResumeRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class DatabaseService(val resumeRepository: ResumeRepository) {
    private val logger = KotlinLogging.logger {}

    fun getResume(): List<Resume> =
        resumeRepository.findAll().toList().map { it.toResume() }

    fun getResume(id: Int): java.util.Optional<ResumeEntity> =
        resumeRepository.findById(id)

    fun createResume(resume: NewResume): Resume? {
        logger.debug { "creating resume with $resume" }
        return resumeRepository.save(resume.toResumeEntity()).toResume();
    }

    fun deleteResume(id: Int) {
        resumeRepository.deleteById(id)
    }

    fun updateResume(resume: Resume): Resume? {
        return null
    }
}