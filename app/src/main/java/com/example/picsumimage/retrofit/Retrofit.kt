package com.example.picsumimage.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object {
        // Static Variable Initialize
        // ADD API Key/Link
        const val URL = "https://picsum.photos/"
        // Function return Retrofit
        // Here GSON is use to fetch JSON Data
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }


    }
}