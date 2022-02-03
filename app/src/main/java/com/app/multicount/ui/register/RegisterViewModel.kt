package com.app.multicount.ui.register

import android.view.View
import androidx.lifecycle.viewModelScope
import com.app.data.usecases.authentication.AccountAuthentication
import com.app.data.utils.DataValidator
import com.app.data.utils.RemoteTask
import com.app.multicount.ui.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val dataValidator: DataValidator,
    private val accountAuthentication: AccountAuthentication,
) : BaseViewModel() {

    val email = MutableStateFlow("")

    val password = MutableStateFlow("")

    val confirmPassword = MutableStateFlow("")

    private val _registerState = Channel<LoginState>(Channel.BUFFERED)
    val registerState = _registerState.receiveAsFlow()

    private val _registerData = MutableStateFlow(RegisterData())
    val registerData: StateFlow<RegisterData> get() = _registerData

    private var isValidEmail=false

    private var isValidPassword=false

    private var isValidConfirmPassword=false

    sealed class LoginState {
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val reason: String) : LoginState()
    }

    data class RegisterData(
        val isValidEmail:Boolean=false,
        val isValidPassword:Boolean=false,
        val isValidConfirmPassword:Boolean=false,
    ){
        fun isValidData() = isValidEmail && isValidPassword && isValidConfirmPassword
    }

    fun validateEmail(email: String):Boolean{
        val isValid = dataValidator.validateEmail(email).isValid()
        _registerData.value = registerData.value.copy(isValidEmail=isValid)
        return isValid
    }

    fun validatePassword(password: String):Boolean{
        val isValid = dataValidator.validatePassword(password).isValid()
        _registerData.value = registerData.value.copy(isValidPassword=isValid)
        return isValid
    }

    fun validateConfirmPassword(password: String, confirmPassword: String):Boolean {
        val isValid = dataValidator.validateConfirmPassword(password, confirmPassword).isValid()
        _registerData.value = registerData.value.copy(isValidConfirmPassword=isValid)
        return isValid
    }


    fun onCreateAccountButtonClicked() {
        viewModelScope.launch {
            accountAuthentication.createAccountWithEmailAndPassword(email.value,password.value).collect {
                when(it){
                    is RemoteTask.Started -> _registerState.send(LoginState.Loading)
                    is RemoteTask.Completed -> _registerState.send(LoginState.Success)
                    is RemoteTask.Error -> _registerState.send(LoginState.Error(reason = it.exception.toString()))
                }
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        accountAuthentication.createAccountWithEmailAndPassword(
            email, password
        )
    }
}