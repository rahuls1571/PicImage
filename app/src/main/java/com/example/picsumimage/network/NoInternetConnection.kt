package com.example.picsumimage.network

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.picsumimage.MainActivity
import com.example.picsumimage.R
import com.google.android.material.snackbar.Snackbar

class NoInternetConnection : AppCompatActivity() {

    lateinit var  Try_Again : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet_connection_layout)

        // Initialize
        Try_Again = findViewById(R.id.Button_Network_Check)


        // Initialize Snackbar
        val snackbar : Snackbar = Snackbar.make(
            findViewById(R.id.no_internet_connection_layout),
            "Offline",
            Snackbar.LENGTH_LONG
        )
        // Show Snackbar
        snackbar.show()

        // Try Again Button for Network check
        Try_Again.setOnClickListener {
            // if network is available then open MainActivity else Try Again
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
            finish()
        }


    }

}