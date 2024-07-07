package project.kloud.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*

fun Application.Serialization() {
    install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
        json()
    }
}