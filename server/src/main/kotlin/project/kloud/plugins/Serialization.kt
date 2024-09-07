package project.kloud.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*

fun Application.configureSerialization() {
    install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
        json()
    }
}