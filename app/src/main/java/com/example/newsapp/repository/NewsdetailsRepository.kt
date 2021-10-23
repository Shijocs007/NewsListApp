package com.example.newsapp.repository

import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.Comments
import com.example.newsapp.models.Likes
import com.example.newsapp.network.LikesApi
import com.example.newsapp.network.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsdetailsRepository constructor(
    private val api : LikesApi,
    private val dao : NewsDao) {

    suspend fun setBookMarked(bookMarked: Boolean, id: String) = dao.setBookmarked(bookMarked, id)

    fun getLikes(url : String) : Flow<Likes> {
        return flow {
            val result = api.getLikes(url)
            emit(result)
        }.catch {  }
    }

   fun getComments(url : String) : Flow<Comments> {
       return flow {
           val result = api.getComments(url)
           emit(result)
       } .catch {  }
   }
}