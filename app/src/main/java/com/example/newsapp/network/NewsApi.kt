package com.example.newsapp.network

import com.example.newsapp.models.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
     * get the list of news from api
     * by default page count is 20 items
     * @param page reprecents the current page
     * @return NewsListResponse
     */
    @GET("v2/top-headlines?country=us")
    suspend fun getNewsList(@Query("page") page : Int = 1) : NewsListResponse
}