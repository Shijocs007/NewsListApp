package com.example.newsapp.repository

import com.example.newsapp.db.NewsDao
import com.example.newsapp.network.NewsApi

class NewsdetailsRepository constructor(private val api : NewsApi, private val dao : NewsDao) {


    suspend fun setBookMarked(bookMarked: Boolean, id: String)  = dao.setBookmarked(bookMarked, id)


}