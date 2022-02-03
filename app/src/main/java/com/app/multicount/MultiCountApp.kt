package com.app.multicount

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.initialize

class MultiCountApp:Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        FirebaseApp.initializeApp(this)
        Firebase.initialize(this)
        initDI()
    }
}