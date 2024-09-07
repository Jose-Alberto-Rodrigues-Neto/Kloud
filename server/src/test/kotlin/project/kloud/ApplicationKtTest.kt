package project.kloud

import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlin.test.Test

class ApplicationKtTest {

    @Test
    fun main() = testApplication {
        environment { config = ApplicationConfig("application.conf") }
    }
}