package com.carrer.resume

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@OpenAPIDefinition

class ResumeBuilderApplication

fun main(args: Array<String>) {
	runApplication<ResumeBuilderApplication>(*args)
}
