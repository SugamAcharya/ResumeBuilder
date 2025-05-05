package com.carrer.resume.integrationtests

import com.carrer.resume.ResumeBuilderApplication
import com.carrer.resume.controller.ResumeController
import com.carrer.resume.model.Resume
import com.carrer.resume.model.Resumes
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ResumeBuilderApplication::class])
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ResumeControllerTests {
    private lateinit var requestSpecification: RequestSpecification

    @LocalServerPort
    private val localPort: Int = 0

    @BeforeEach
    fun setUp() {
        requestSpecification =
            Given {
                baseUri("http://localhost")
                port(localPort)
                log().all()
                response().log().all()
                contentType(ContentType.JSON)
                accept(ContentType.JSON)
            }
    }

    @Test
    internal fun `Verify that GET request on the v1 resume endpoint will return Member1 and Member2`() {
        val response =
            Given {
                spec(requestSpecification)
            } When {
                get("/v1/resume")
            } Then {
                statusCode(200)
            } Extract {
                body().`as`(Resumes::class.java)
            }
        assertThat(response.resume).hasSize(2)
        assertMember1(response.resume.first())
        assertMember2(response.resume.last())
    }

    @Test
    internal fun `Verify that the GET request on the v1 single resume retruns Member1`() {
        val response =
            Given {
                spec(requestSpecification)
            } When {
                get("/v1/resume/1")
            } Then {
                statusCode(200)
            } Extract {
                body().`as`(Resume::class.java)
            }
        assertMember1(response)
    }

    @Test
    internal fun `Veify that POST request on the v1 resume endpoint stores the resume data`() {
        val response = Given {
            spec(requestSpecification)
        } When {
            body(
                """{
                "name": "Member3",
                "address": "Address3",
                "contact": "Contact3",
                "professionalSummary": "Hello, my name is Member3. I am professional.",
                "skills": "Book Keeping, Maintaining"
            }"""
            )
            post("/v1/resume")
        } Then {
            statusCode(200)
        } Extract {
            body().`as`(Resume::class.java)
        }
        assertMember3(response)
    }

    private fun assertMember1(member1: Resume) {
        assertThat(member1.name).isEqualTo(FIRST_MEMBER)
        assertThat(member1.address).isEqualTo(FIRST_ADDRESS)
        assertThat(member1.contact).isEqualTo(FIRST_CONTACT)
        assertThat(member1.professionalSummary).isEqualTo(FIRST_SUMMARY)
    }

    private fun assertMember2(member2: Resume) {
        assertThat(member2.name).isEqualTo(SECOND_MEMBER)
        assertThat(member2.address).isEqualTo(SECOND_ADDRESS)
        assertThat(member2.contact).isEqualTo(SECOND_CONTACT)
        assertThat(member2.professionalSummary).isEqualTo(SECOND_SUMMARY)
    }
    private fun assertMember3(member3: Resume) {
        assertThat(member3.name).isEqualTo(THIRD_MEMBER)
        assertThat(member3.address).isEqualTo(THIRD_ADDRESS)
        assertThat(member3.contact).isEqualTo(THIRD_CONTACT)
        assertThat(member3.professionalSummary).isEqualTo(THIRD_SUMMARY)
    }

    companion object {
        val FIRST_MEMBER = "Member1"
        val FIRST_ADDRESS = "Address1"
        val FIRST_CONTACT = "Contact1"
        val FIRST_SUMMARY = "FIRST PROF SUMMARY"

        val SECOND_MEMBER = "Member2"
        val SECOND_ADDRESS = "Address2"
        val SECOND_CONTACT = "Contact2"
        val SECOND_SUMMARY = "SECOND PROF SUMMARY"

        val THIRD_MEMBER = "Member3"
        val THIRD_ADDRESS = "Address3"
        val THIRD_CONTACT = "Contact3"
        val THIRD_SUMMARY = "Hello, my name is Member3. I am professional."
    }

}