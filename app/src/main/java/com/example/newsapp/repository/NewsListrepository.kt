package com.example.newsapp.repository

import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.NewsListResponse
import com.example.newsapp.network.NewsApi
import om.example.newsapp.db.NewsDatabase
import retrofit2.Response

class NewsListrepository constructor(private val api : NewsApi, private val dao : NewsDao) {

    suspend fun getMoviesList(): Response<NewsListResponse> {
        return api.getNewsList()
    }
}