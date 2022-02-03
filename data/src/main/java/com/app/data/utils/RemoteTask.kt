package com.app.data.utils

sealed class RemoteTask<out T : Any> {

    class Started<out T : Any> : RemoteTask<T>()

    class Completed<out T : Any>(val result: T) : RemoteTask<T>()

    class Error<out T : Any>(val exception: Exception) : RemoteTask<T>()

    override fun toString(): String {
        return when(this){
            is Started -> "Started"
            is Completed -> "Success[data=$result]"
            is Error -> "Error[exception=$exception]"
        }
    }
}