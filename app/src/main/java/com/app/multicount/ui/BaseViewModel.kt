package com.app.multicount.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel:ViewModel() {

    sealed class Event {
        class Navigate(val direction: NavDirections):Event()
        object NavigateUp:Event()
        class ShowSnackBar(@StringRes val text:Int):Event()
    }

    val _event = Channel<Event>(Channel.BUFFERED)
    open val event = _event.receiveAsFlow()

}