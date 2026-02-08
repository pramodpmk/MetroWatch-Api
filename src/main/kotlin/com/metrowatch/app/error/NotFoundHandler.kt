package com.metrowatch.app.error

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Singleton
class NotFoundHandler :
    ExceptionHandler<NoSuchElementException, HttpResponse<ApiError>> {

    override fun handle(request: HttpRequest<*>, exception: NoSuchElementException)
            : HttpResponse<ApiError> {

        return HttpResponse.notFound(
            ApiError(
                status = 404,
                error = "Not Found",
                message = exception.message,
                path = request.path
            )
        )
    }
}
