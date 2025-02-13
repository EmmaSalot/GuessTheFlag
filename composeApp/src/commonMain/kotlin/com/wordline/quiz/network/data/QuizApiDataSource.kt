package com.wordline.quiz.network.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val globalHttpClient = HttpClient {
    engine {

    }

    install(ContentNegotiation) {
        json(
            contentType = ContentType.Text.Plain, // because Github is not returning an 'application/json' header
            json = Json {
                ignoreUnknownKeys = true
                explicitNulls = false
                useAlternativeNames = false
            })
    }
}

class QuizApiDatasource {
    private val httpClient = globalHttpClient
    suspend fun getAllCountries(): Quiz {
        return httpClient.get("https://raw.githubusercontent.com/alexis-bonal/country-flags/refs/heads/main/countries.json").body()
    }
}