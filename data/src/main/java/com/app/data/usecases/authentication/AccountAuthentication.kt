package com.app.data.usecases.authentication

import com.app.data.repositories.AuthenticationRepository

class AccountAuthentication(
    private val authenticationRepository: AuthenticationRepository
) {
    fun loginWithEmailAndPassword(email:String,password:String) = authenticationRepository.loginWithEmailAndPassword(email, password)

    fun loginWithGoogleAccount() = authenticationRepository.loginWithGoogleAccount()

    fun loginWithFacebookAccount() = authenticationRepository.loginWithFacebookAccount()

    fun createAccountWithEmailAndPassword(email:String, password:String) = authenticationRepository.createAccountWithEmailAndPassword(email, password)

    fun createAccountWithGoogle() = authenticationRepository.createAccountWithGoogle()

    fun createAccountWithFacebook() = authenticationRepository.createAccountWithFacebook()

    fun createGuestAccount() = authenticationRepository.createGuestAccount()

}