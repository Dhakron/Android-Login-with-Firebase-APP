package com.app.multicount.ui.main

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.app.data.usecases.authentication.IsUserAuthenticated
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val isUserAuthenticated: IsUserAuthenticated
):ViewModel() {

    private val _uiEvent = Channel<UIEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    sealed class UIEvent {
        class Navigate(val direction: NavDirections):UIEvent()
        object NavigateUp:UIEvent()
        class ShowSnackBar(@StringRes val text:Int):UIEvent()
        object UserAuthenticated:UIEvent()
        object IncompleteUserAuthenticated:UIEvent()
        object NeedUserAuthentication:UIEvent()
        object LogOut:UIEvent()
    }

    init {
        checkLoggedUser()
    }

    private fun checkLoggedUser(){
        when{
            isUserAuthenticated()->onUserAuthenticated()
            else -> onUserNeedAuthentication()
        }
    }

    fun onUserAuthenticated(){
        viewModelScope.launch {
            _uiEvent.send(UIEvent.UserAuthenticated)
        }
    }

    private fun onUserNeedAuthentication(){
        viewModelScope.launch {
            _uiEvent.send(UIEvent.NeedUserAuthentication)
        }
    }

    fun onUserLogOut(){
        viewModelScope.launch {
            _uiEvent.send(UIEvent.LogOut)
        }
    }
}