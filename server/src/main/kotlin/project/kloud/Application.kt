package project.kloud

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import project.kloud.plugins.configureExceptionHandler
import project.kloud.plugins.configureSerialization
import project.kloud.plugins.configureSession
import java.time.Instant
import java.util.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)


const val PING_PARAMETER_EXCEPTION_MESSAGE = "The ping parameter must be a number"

object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = String.serializer().descriptor

    override fun deserialize(decoder: Decoder): Instant =
        Instant.parse(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Instant) =
        encoder.encodeString(value.toString())
}

@Serializable
data class Pong(
    @Serializable(with = InstantSerializer::class)
    val deployedAt: Instant,
    val pong: Long,
)

fun Application.isAlive() {
    val deployedAt = Date().toInstant()
    configureExceptionHandler()
    configureSerialization()
    configureSession()

    routing {
        get("/is-alive") {
            call
            val plusOne = call.parameters["ping"]
                ?.toLongOrNull()
                ?.let { it + 1 }
            requireNotNull(plusOne) { PING_PARAMETER_EXCEPTION_MESSAGE }

            call.respond(HttpStatusCode.OK, Pong(deployedAt, plusOne))
        }
    }
}