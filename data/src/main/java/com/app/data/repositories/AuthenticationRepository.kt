package com.app.data.repositories

import com.app.data.source.authentication.AuthenticationDataSource

class AuthenticationRepository(
    private val dataSource: AuthenticationDataSource
) {
    fun isUserLogged() = dataSource.isUserLogged()

    fun getLoggedUserUID() = dataSource.getLoggedUserUID()

    fun isGuestUserLogged() = dataSource.isGuestUserLogged()

    fun createAccountWithEmailAndPassword(email:String, password:String) = dataSource.createUserWithEmailAndPassword(email, password)

    fun createAccountWithGoogle() = dataSource.createUserWithGoogleAccount()

    fun createAccountWithFacebook() = dataSource.createUserWithFacebookAccount()

    fun createGuestAccount() = dataSource.createGuestUser()

    fun loginWithEmailAndPassword(email:String,password:String) = dataSource.loginWithEmailAndPassword(email, password)

    fun loginWithGoogleAccount() = dataSource.loginWithGoogleAccount()

    fun loginWithFacebookAccount() = dataSource.loginWithFacebookAccount()

    fun logOut() = dataSource.logOut()

    fun changePassword(newPassword:String) = dataSource.changePassword(newPassword)

    fun recoverPassword(email: String) = dataSource.recoverPassword(email)

}