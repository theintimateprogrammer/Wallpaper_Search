package com.example.wallpaper.API

import com.example.wallpaper.Model.PhotoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Apiinterface {

    @GET("search")

    fun getphotos(
        @Header("Authorization")auth: String,
        @Query("query")query: String
    ): Call<PhotoModel>
}
