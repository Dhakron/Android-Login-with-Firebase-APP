package com.app.data.usecases.authentication

import com.app.data.repositories.AuthenticationRepository

class RecoverUserAccountPassword(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email:String) = authenticationRepository.recoverPassword(email)
}