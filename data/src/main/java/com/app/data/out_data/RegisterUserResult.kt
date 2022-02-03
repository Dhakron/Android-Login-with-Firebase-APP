package com.app.data.out_data

import com.app.data.domain.User

sealed class RegisterUserResult{
    object Success:RegisterUserResult()
    data class Failure(val reason: Reason):RegisterUserResult(){
        enum class Reason{
            AccountAlreadyExist,
            UnreachableServer,
        }
    }
}