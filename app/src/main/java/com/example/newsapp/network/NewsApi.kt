package com.example.newsapp.network

import com.example.newsapp.models.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/everything?q=football")
    suspend fun getNewsList(@Query("page") page : Int = 1) : Response<NewsListResponse>
}