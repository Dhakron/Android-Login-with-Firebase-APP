package com.app.data.usecases.authentication

import com.app.data.repositories.AuthenticationRepository

class ChangeLoggedUserPassword(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(newPassword: String) = authenticationRepository.changePassword(newPassword)
}