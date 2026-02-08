package com.metrowatch.app.error

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Singleton
class S3ReadExceptionHandler :
    ExceptionHandler<S3ReadInterruptedException, HttpResponse<ApiError>> {

    override fun handle(
        request: HttpRequest<*>?,
        exception: S3ReadInterruptedException?
    ): HttpResponse<ApiError>? {
        return HttpResponse.notFound(
            ApiError(
                System.currentTimeMillis(),
                404,
                "S3 Read Error",
                exception?.message,
                request?.path
            )
        )
    }
}