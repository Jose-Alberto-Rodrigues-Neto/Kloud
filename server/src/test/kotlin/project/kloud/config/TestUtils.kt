package project.kloud.config

import arrow.core.raise.ResultRaise
import arrow.core.raise.ensure
import arrow.core.raise.result
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

object TestUtils {
    context(ApplicationTestBuilder)
    suspend fun HttpClient.expectResponse(
        expect: HttpStatusCode,
        httpRequest: HttpRequestBuilder,
        block: suspend ResultRaise.(HttpResponse) -> Unit,
    ) = result {
        val response: HttpResponse = request(httpRequest)

        ensure(response.status == expect) {
            AssertionError("Expected status $expect but got ${response.status}")
        }

        block(response)
    }.onFailure { error -> throw AssertionError(error.message) }
}