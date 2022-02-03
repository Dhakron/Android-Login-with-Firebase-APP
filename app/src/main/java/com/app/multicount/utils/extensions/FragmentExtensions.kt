package com.app.multicount.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

fun Fragment.navigate(direction:NavDirections):Boolean{
    return try {
        findNavController().navigate(direction)
        true
    }catch (e:Exception){
        Timber.e(e)
        false
    }
}

fun Fragment.navigateUp():Boolean{
    return try {
        findNavController().navigateUp()
        true
    }catch (e:Exception){
        Timber.e(e)
        false
    }
}

fun <T> Fragment.observe(flow: Flow<T>, onCollect: (value:T)->Unit){
    addRepeatingJob(Lifecycle.State.STARTED) {
        flow.collect { onCollect.invoke(it) }
    }
}

fun <T> Fragment.observeEvent(flow: Flow<T>, onCollect: (value:T)->Unit){
    flow.onEach { onCollect.invoke(it) }.launchIn(lifecycleScope)
}

fun <T> Fragment.observe(flow: Flow<T>, onCollect: (value:T)->Unit, lifecycleState: Lifecycle.State = Lifecycle.State.STARTED){
    addRepeatingJob(lifecycleState) {
        flow.collect { onCollect.invoke(it) }
    }
}

fun <T> Fragment.observeEvent(flow: Flow<T>, onCollect: (value:T)->Unit,lifecycleState: Lifecycle.State = Lifecycle.State.STARTED){
    flow.onEach { onCollect.invoke(it) }.launchIn(lifecycleScope)
}