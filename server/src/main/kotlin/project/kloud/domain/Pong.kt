package project.kloud.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant

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
    val applicationStart: Instant,
    val pong: Long,
)
