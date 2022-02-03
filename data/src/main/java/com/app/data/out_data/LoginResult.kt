package com.app.data.out_data

import com.app.data.domain.User

sealed class LoginResult{
    object Success:LoginResult()
    data class Failure(val reason: Reason):LoginResult(){
        enum class Reason{
            UnregisteredAccount,
            WrongCredentials,
            UnreachableServer,
        }
    }
}