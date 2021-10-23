package com.example.newsapp.repository

import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.News
import com.example.newsapp.models.NewsListResponse
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.networkBoundResource
import kotlinx.coroutines.flow.Flow
import om.example.newsapp.db.NewsDatabase
import retrofit2.Response

//https://developer.android.com/jetpack/guide?hl=de#addendum

class NewsListrepository constructor(private val api : NewsApi, private val dao : NewsDao) {

    val POST_PER_PAGE = 20

    fun getMoviesList() = networkBoundResource(
        query = {
            dao.getMovies(POST_PER_PAGE, 1)
        },
        fetch = {
            api.getNewsList(1)
        },
        saveFetchResult = {response ->
            response.articles?.let {dao.upsertAll(it) }
        }
    )

    fun getBookMarkedList(): Flow<List<News>> {
        return dao.getBookMarkedList()
    }
}