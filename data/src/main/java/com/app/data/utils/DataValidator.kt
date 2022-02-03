package com.app.data.utils

interface DataValidator {

    fun validateEmail(email:String):Result

    fun validatePassword(password:String):Result

    fun validateConfirmPassword(password: String, confirmPassword:String):Result

    sealed class Result{
        object ValidData : Result()
        class InvalidData(val reason:String) : Result()

        fun isValid():Boolean = this == ValidData

        fun getInvalidReason() : String? = (this as? InvalidData)?.reason
    }

    companion object{
        const val PASSWORD_MIN_LENGTH = 6
        const val PASSWORD_MAX_LENGTH = 64
    }
}