package com.app.multicount.data

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

class ConnectionManager {
    class ConnectivityLiveData(val context: Context) : LiveData<ConnectivityLiveData.NetworkState>() {
        enum class NetworkState {
            CONNECTED,
            DISCONNECTED,
            UNINITIALIZED
        }

        private var connectivityManager: ConnectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        private var mPreviousState = NetworkState.UNINITIALIZED

        private lateinit var networkCallback: ConnectivityManager.NetworkCallback

        override fun onActive() {
            super.onActive()
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(getConnectivityManagerCallback())
                else -> lollipopNetworkAvailableRequest()
            }
        }

        override fun onInactive() {
            super.onInactive()
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }

        private fun lollipopNetworkAvailableRequest() {
            val builder = NetworkRequest.Builder()
                .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI)
            connectivityManager.registerNetworkCallback(builder.build(), getConnectivityManagerCallback())
        }

        private fun getConnectivityManagerCallback(): ConnectivityManager.NetworkCallback {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    postValue(NetworkState.CONNECTED)
                }

                override fun onLost(network: Network) {
                    postValue(NetworkState.DISCONNECTED)
                }
            }
            return networkCallback
        }

    }
}