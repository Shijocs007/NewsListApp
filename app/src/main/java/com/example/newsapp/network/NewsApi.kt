package com.example.newsapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/")
    suspend fun getNewsList(@Query("page") page : Int ) : Unit
}