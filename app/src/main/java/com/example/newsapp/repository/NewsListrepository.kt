package com.example.newsapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.News
import com.example.newsapp.network.NewsApi
import kotlinx.coroutines.flow.Flow

class NewsListrepository constructor(
    private val api : NewsApi,
    private val dao : NewsDao,
    private val source: NewsListPagingSource) {

    /**
     * get the list of news with jetpack paging library
     * @return flow object of paging library
     */
    fun getNewsList() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { source }
    ).flow

    /**
     * get the bookmarked news saved in local db
     * @return list of book marked news
     */
    fun getBookMarkedList(): Flow<List<News>> {
        return dao.getBookMarkedList()
    }
}