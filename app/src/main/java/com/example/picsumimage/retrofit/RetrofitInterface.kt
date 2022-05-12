package com.example.picsumimage.retrofit

import com.example.picsumimage.model.PicSumModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    // ADD Fetch Data Path
    @GET("v2/list?")
    // Function Return List of Model
    // Add Query page number
   suspend fun getModel(@Query("page") page : Int): List<PicSumModel>
}