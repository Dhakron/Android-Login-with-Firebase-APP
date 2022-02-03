package com.app.multicount.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.usecases.authentication.AuthenticationLogOut
import com.app.multicount.ui.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logOut: AuthenticationLogOut
) : BaseViewModel() {

    private val _homeEvent = Channel<HomeEvent>(Channel.BUFFERED)
    val homeEvent = _homeEvent.receiveAsFlow()

    sealed class HomeEvent{
        object LogOut:HomeEvent()
    }

    fun onLogOutClicked(){
        viewModelScope.launch {
            if(logOut()){
                _homeEvent.send(HomeEvent.LogOut)
            }
        }
    }

}