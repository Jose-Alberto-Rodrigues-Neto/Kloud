package project.kloud.plugins

import io.ktor.server.application.*
import io.ktor.server.sessions.*


fun Application.configureSession() {
    install(Sessions) {
        cookie<UserSession>("session") {
            cookie.maxAgeInSeconds = 3600
        }
    }
}