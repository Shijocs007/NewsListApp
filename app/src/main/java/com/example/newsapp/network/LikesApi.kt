package com.example.newsapp.network

import com.example.newsapp.models.Comments
import com.example.newsapp.models.Likes
import com.example.newsapp.models.NewsListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LikesApi {

    @GET("likes/{url}")
    suspend fun getLikes(@Path("url") url : String) : Likes

    @GET("comments/{url}")
    suspend fun getComments(@Path("url") url : String) : Comments
}