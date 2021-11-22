package com.german.notificationappkotlin.controllers

import com.german.notificationappkotlin.controllers.dto.ComplexClientDTO
import com.german.notificationappkotlin.domain.Medias
import com.german.notificationappkotlin.external.RestTemplateService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ExtendWith(MockKExtension::class)
@ActiveProfiles("test")
class ClientControllerTest {

    @MockK
    lateinit var restTemplateService: RestTemplateService

    @InjectMockKs
    lateinit var clientController: ClientController

    @Test
    fun successfully_get_getClientFromExternalSource_test() {
        // Given
        val clientID = 123L
        every {
            restTemplateService.getComplexClient(clientID).body
        } answers {
            ComplexClientDTO(
                "This is the description for the complex client",
                "Mister important", Medias.MAIL,
                "someemail@gmail.com"
            )
        }

        // When
        val expectedResponse = clientController.getClientFromExternalSource(clientID)

        // Then
        verify(exactly = 1) { restTemplateService.getComplexClient(123L) }

        Assertions.assertThat(
            expectedResponse ==
                    ResponseEntity.ok(
                        ComplexClientDTO(
                            "This is the description for the complex client",
                            "Mister important", Medias.MAIL,
                            "someemail@gmail.com"
                        )
                    )
        )
    }

    @Test
    fun `We do not find a client so returns 404`() {

        // Given
        val clientID = 321L

        every {
            restTemplateService.getComplexClient(clientID)
        } answers {
            ResponseEntity(
                "{\n" +
                        "\"message\" : \"The client was not found\"\n" +
                        "}", HttpStatus.NOT_FOUND
            )
        }

        // When
        val response = clientController.getClientFromExternalSource(clientID)
        // Then
        Assertions.assertThat(
            response == ResponseEntity(
                "{\n" +
                        "\"message\" : \"The client was not found\"\n" +
                        "}", HttpStatus.NOT_FOUND
            )
        )
    }

}