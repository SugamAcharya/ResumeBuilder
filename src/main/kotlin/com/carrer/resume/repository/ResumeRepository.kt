package com.carrer.resume.repository

import com.carrer.resume.entity.ResumeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ResumeRepository: CrudRepository<ResumeEntity, Int> {
}