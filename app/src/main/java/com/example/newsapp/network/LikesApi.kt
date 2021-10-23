package com.example.newsapp.network

import com.example.newsapp.models.Comments
import com.example.newsapp.models.Likes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LikesApi {

    /**
     * get the number of likes for this article
     */
    @GET("likes/{url}")
    suspend fun getLikes(@Path("url") url : String) : Likes

    /**
     * get the nymber of comments for this article
     */
    @GET("comments/{url}")
    suspend fun getComments(@Path("url") url : String) : Comments
}