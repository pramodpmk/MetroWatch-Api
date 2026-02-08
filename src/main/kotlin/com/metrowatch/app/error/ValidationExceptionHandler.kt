package com.metrowatch.app.error
//
//import io.micronaut.http.HttpRequest
//import io.micronaut.http.HttpResponse
//import io.micronaut.http.server.exceptions.ExceptionHandler
//import jakarta.inject.Singleton
//
//@Singleton
//class ValidationExceptionHandler :
//    ExceptionHandler<ConstraintViolationException, HttpResponse<ApiError>> {
//
//    override fun handle(request: HttpRequest<*>, exception: ConstraintViolationException)
//            : HttpResponse<ApiError> {
//
//        val msg = exception.constraintViolations
//            .joinToString { it.message }
//
//        return HttpResponse.badRequest(
//            ApiError(400, "Bad Request", msg, request.path)
//        )
//    }
//}
