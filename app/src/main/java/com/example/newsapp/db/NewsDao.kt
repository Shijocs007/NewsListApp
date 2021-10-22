package com.example.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertAll(news: List<News>)

    @Query("SELECT * FROM News LIMIT :limit OFFSET :offset")
    fun getMovies(limit: Int, offset: Int): Flow<List<News>>
}