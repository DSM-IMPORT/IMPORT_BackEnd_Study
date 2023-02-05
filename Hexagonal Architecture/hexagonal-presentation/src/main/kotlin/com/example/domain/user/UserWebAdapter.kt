package com.example.domain.user

import com.example.domain.user.dto.SignupWebRequest
import com.example.domain.user.dto.SignupRequest
import com.example.domain.user.usecase.SignupUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/users")
class UserWebAdapter(
    private val signupUseCase: SignupUseCase
) {

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @RequestBody @Valid
        request: SignupWebRequest
    ) {
        signupUseCase.execute(
            SignupRequest(
            request.accountId,
            request.password,
            request.name,
            request.age
            )
        )
    }
}