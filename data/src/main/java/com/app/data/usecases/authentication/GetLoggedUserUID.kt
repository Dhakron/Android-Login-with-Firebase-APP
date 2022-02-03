package com.app.data.usecases.authentication

import com.app.data.repositories.AuthenticationRepository

class GetLoggedUserUID(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke() = authenticationRepository.getLoggedUserUID()
}