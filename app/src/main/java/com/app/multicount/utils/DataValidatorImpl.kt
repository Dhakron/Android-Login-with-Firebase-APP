package com.app.multicount.utils

import android.content.res.Resources
import com.app.data.utils.DataValidator
import com.app.data.utils.DataValidator.Companion.PASSWORD_MAX_LENGTH
import com.app.data.utils.DataValidator.Companion.PASSWORD_MIN_LENGTH
import com.app.multicount.R

class DataValidatorImpl(
    private val resources: Resources
) : DataValidator {
    override fun validateEmail(email: String) =
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            DataValidator.Result.ValidData
        } else {
            DataValidator.Result.InvalidData(reason = resources.getString(R.string.invalid_email))
        }

    override fun validatePassword(password: String) = when {
        password.length < PASSWORD_MIN_LENGTH -> DataValidator.Result.InvalidData(
            reason = resources.getString(R.string.invalid_password_min_length, PASSWORD_MIN_LENGTH)
        )
        password.length > PASSWORD_MAX_LENGTH -> DataValidator.Result.InvalidData(
            reason = resources.getString(R.string.invalid_password_max_length, PASSWORD_MAX_LENGTH)
        )
        else -> DataValidator.Result.ValidData
    }

    override fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ) = if (password == confirmPassword) {
        DataValidator.Result.ValidData
    } else {
        DataValidator.Result.InvalidData(
            reason = resources.getString(R.string.invalid_confirm_password)
        )
    }
}