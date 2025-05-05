package com.carrer.resume.controller

import com.carrer.resume.entity.toResume
import com.carrer.resume.model.NewResume
import com.carrer.resume.model.Resume
import com.carrer.resume.model.Resumes
import com.carrer.resume.service.DatabaseService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/v1")
class ResumeController(
    private val dbService: DatabaseService
) {

    @GetMapping("/resume")
    fun listResume(): ResponseEntity<Resumes> {
        return ResponseEntity.ok(Resumes(dbService.getResume()))
    }

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200"),
            ApiResponse(responseCode = "404", description = "Resume not found"),
            ApiResponse(responseCode = "500", description = "Internal Server Error")
        ]
    )
    @GetMapping("/resume/{id}")
    fun getResume(@PathVariable id: Int): ResponseEntity<Resume> {
        val existingResume = dbService.getResume(id)
        return if (existingResume.isPresent) {
            ResponseEntity.ok(existingResume.get().toResume())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200"),
            ApiResponse(responseCode = "500", description = "Internal Server Error")
        ]
    )
    @PostMapping("/resume")
    fun createResume(@RequestBody resume: NewResume): ResponseEntity<Resume> {
        val createdResume = dbService.createResume(resume)
        return ResponseEntity.ok().body(createdResume)
    }

    fun deleteResume(@PathVariable id: Int) {
        dbService.deleteResume(id)
    }
}