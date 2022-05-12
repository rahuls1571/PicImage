package com.example.picsumimage.network

import android.app.Application

class NetworkApplication : Application() {

    companion object{
        // Initialize NetworkApplication
        lateinit var networkApplication: NetworkApplication
        // Get Network Application Instance
        fun getInstance() : NetworkApplication{
            return networkApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        networkApplication = this
    }

    // Set Connectivity Listner
    fun SetConnectivityListner(lister : InternetReceiver.InternetConnectListner){
        InternetReceiver.internetConnectListner = lister
    }

}