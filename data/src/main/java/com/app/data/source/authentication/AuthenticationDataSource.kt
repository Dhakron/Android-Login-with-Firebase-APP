package com.app.data.source.authentication

import com.app.data.out_data.LoginResult
import com.app.data.out_data.RegisterUserResult
import com.app.data.utils.RemoteTask
import kotlinx.coroutines.flow.Flow

interface AuthenticationDataSource {

    fun isUserLogged() : Boolean

    fun getLoggedUserUID() : String?

    fun isGuestUserLogged() : Boolean

    fun createUserWithEmailAndPassword(email:String,password:String) : Flow<RemoteTask<RegisterUserResult>>

    fun createUserWithGoogleAccount() : Flow<RemoteTask<RegisterUserResult>>

    fun createUserWithFacebookAccount() : Flow<RemoteTask<RegisterUserResult>>

    fun createGuestUser() : Flow<RemoteTask<RegisterUserResult>>

    fun loginWithEmailAndPassword(email:String,password:String) : Flow<RemoteTask<LoginResult>>

    fun loginWithGoogleAccount() : Flow<RemoteTask<LoginResult>>

    fun loginWithFacebookAccount() : Flow<RemoteTask<LoginResult>>

    fun logOut() : Boolean

    fun changePassword(newPassword:String) : Flow<RemoteTask<Boolean>>

    fun recoverPassword(email: String) : Flow<RemoteTask<Boolean>>

}