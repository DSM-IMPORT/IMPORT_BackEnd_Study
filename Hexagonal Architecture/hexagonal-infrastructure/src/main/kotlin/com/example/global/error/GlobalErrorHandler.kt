package com.example.global.error

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(e: BindException): BindErrorResponse? = ErrorResponse.of(e)

    @ExceptionHandler(CustomException::class)
    protected fun customExceptionHandle(e: CustomException) = ErrorResponse(
        e.errorProperty.status(),
        e.errorProperty.message()
    )

}