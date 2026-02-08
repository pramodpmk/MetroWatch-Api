package com.metrowatch.app.error

import io.micronaut.http.*
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Produces
@Singleton
class GlobalExceptionHandler :
    ExceptionHandler<Throwable, HttpResponse<ApiError>> {

    override fun handle(request: HttpRequest<*>, exception: Throwable): HttpResponse<ApiError> {

        val (status, message) = when (exception) {

            is IllegalArgumentException ->
                HttpStatus.BAD_REQUEST to exception.message

            is NoSuchElementException ->
                HttpStatus.NOT_FOUND to exception.message

            else ->
                HttpStatus.INTERNAL_SERVER_ERROR to "Something went wrong"
        }

        val error = ApiError(
            status = status.code,
            error = status.reason,
            message = message,
            path = request.path
        )

        return HttpResponse.status<ApiError>(status).body(error)
    }
}
