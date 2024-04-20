package project.kloud

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ModuleTest {

    @Test
    fun testGetIsAlive() = testApplication {
        application {
            module()
        }

        client.get("/is-alive") {
            parameter("ping", "1")
        }.apply {
            assert(status == HttpStatusCode.OK)
            assertEquals("pong 2", bodyAsText())
        }
    }

    @Test
    fun testGetIsAliveWithoutPing() = testApplication {
        application {
            module()
        }

        client.get("/is-alive").apply {
            assert(status == HttpStatusCode.BadRequest)
            assertEquals("ping parameter is missing", bodyAsText())
        }
    }
}