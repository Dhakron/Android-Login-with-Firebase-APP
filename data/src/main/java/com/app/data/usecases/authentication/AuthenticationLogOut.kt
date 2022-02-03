package com.app.data.usecases.authentication

import com.app.data.repositories.AuthenticationRepository

class AuthenticationLogOut(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke() = authenticationRepository.logOut()
}