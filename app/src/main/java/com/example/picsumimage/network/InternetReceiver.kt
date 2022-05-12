package com.example.picsumimage.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
// Implement Broadcast Receiver for Internet connectivity check

class InternetReceiver : BroadcastReceiver() {

    companion object{
        // Initialize network listner
        lateinit var internetConnectListner: InternetConnectListner
        // isConnected function to check network is available or not
        fun isConnected() : Boolean{
            // Initialize connectivityManager
            val connectivityManager : ConnectivityManager = NetworkApplication.getInstance().applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        // Initialize connectivityManager
        val connectivityManager : ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Get Network Information
        val activeNetwork = connectivityManager.activeNetworkInfo
        // Get is connected or not
        val isConnected  = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if(internetConnectListner != null){
            internetConnectListner.OnConnected(isConnected)
        }
    }

    // Initialize Internet Connect Listner
    interface InternetConnectListner {
        fun OnConnected(isConnected: Boolean)
    }
}