package com.example.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsapp.models.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(news : List<News>)

//    @Query("SELECT * FROM News LIMIT :limit OFFSET :offset")
//    suspend fun getMovies(limit : Int, offset : Int) : List<News>
}