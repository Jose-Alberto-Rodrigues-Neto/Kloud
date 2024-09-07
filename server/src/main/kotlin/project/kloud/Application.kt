package project.kloud

import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import project.kloud.plugins.configureAuthProvider
import project.kloud.plugins.configureExceptionHandler
import project.kloud.plugins.configureSerialization
import project.kloud.plugins.configureSession

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

val env = dotenv()

const val PING_PARAMETER_EXCEPTION_MESSAGE = "The ping parameter must be a number"

fun Application.isAlive() {
    configureExceptionHandler()
    configureSerialization()
    configureSession()
    configureAuthProvider()

    routing {
        get("/is-alive") {
            call.apply {
                val plusOne = parameters["ping"]
                    ?.toLongOrNull()
                    ?.let { it + 1 }
                requireNotNull(plusOne) { PING_PARAMETER_EXCEPTION_MESSAGE }


                return@get respondText("pong $plusOne")
            }
        }
    }
}