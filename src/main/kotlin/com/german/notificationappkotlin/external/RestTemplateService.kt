package com.german.notificationappkotlin.external

import com.german.notificationappkotlin.config.NotificationAppConfiguration
import com.german.notificationappkotlin.controllers.dto.ComplexClientDTO
import com.german.notificationappkotlin.log.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class RestTemplateService {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = Utils.getLogger(javaClass.enclosingClass)
    }

    @Autowired
    lateinit var notificationAppConfiguration: NotificationAppConfiguration

    @Autowired
    lateinit var restTemplate: RestTemplate

    fun getComplexClient(clientID: Long): ResponseEntity<ComplexClientDTO> = try {
        restTemplate.getForEntity(
            UriComponentsBuilder
                .fromHttpUrl(notificationAppConfiguration.url)
                .path("/client/$clientID")
                .build()
                .toUri(),
            ComplexClientDTO::class.java
        )
    } catch (e: RestClientResponseException) {
        ResponseEntity.status(e.rawStatusCode).build()
    }

//    fun getForObject(): Tattoo? = try {
//        restTemplate.getForObject(
//            UriComponentsBuilder
//                .fromHttpUrl(tattooServiceConfiguration.url)
//                .path("/tattoo/123")
//                .build()
//                .toUri(),
//            Tattoo::class.java
//        )
//    } catch (e: RestClientResponseException) {
//        null
//    }
//
//    fun postForEntity(): ResponseEntity<TattooPostResult> = try {
//        restTemplate.postForEntity(
//            UriComponentsBuilder
//                .fromHttpUrl(tattooServiceConfiguration.url)
//                .path("/tattoo/123")
//                .build()
//                .toUri(),
//            Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool),
//            TattooPostResult::class.java
//        )
//    } catch (e: RestClientResponseException) {
//        ResponseEntity.status(e.rawStatusCode).build()
//    }
//
//    fun postForObject(): TattooPostResult? = try {
//        restTemplate.postForObject(
//            UriComponentsBuilder
//                .fromHttpUrl(tattooServiceConfiguration.url)
//                .path("/tattoo/123")
//                .build()
//                .toUri(),
//            Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool),
//            TattooPostResult::class.java
//        )
//    } catch (e: RestClientResponseException) {
//        null
//    }
//
//    fun put(): String = try {
//        val tattoo = Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool)
//        restTemplate.put(
//            UriComponentsBuilder
//                .fromHttpUrl(tattooServiceConfiguration.url)
//                .path("/tattoo/123")
//                .build()
//                .toUri(),
//            tattoo,
//        )
//        "Tattoo resource created $tattoo"
//    } catch (e: RestClientResponseException) {
//        val error = "Put client error ${e.rawStatusCode}"
//        logger.error(error)
//        error
//    }
//
//    fun delete(): String = try {
//        restTemplate.delete(
//            UriComponentsBuilder
//                .fromHttpUrl(tattooServiceConfiguration.url)
//                .path("/tattoo/123")
//                .build()
//                .toUri()
//        )
//        "Tattoo resource deleted"
//    } catch (e: RestClientResponseException) {
//        val error = "Put client error ${e.rawStatusCode}"
//        logger.error(error)
//        error
//    }
//
//    fun exchange(): ResponseEntity<TattooPostResult> = try {
//        restTemplate.exchange(
//            UriComponentsBuilder
//                .fromHttpUrl(tattooServiceConfiguration.url)
//                .path("/tattoo/123")
//                .build()
//                .toUri(),
//            HttpMethod.POST,
//            HttpEntity<Tattoo>(Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool)),
//            TattooPostResult::class.java
//        )
//    } catch (e: RestClientResponseException) {
//        ResponseEntity.status(e.rawStatusCode).build()
//    }
}