package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.db.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import om.example.newsapp.db.NewsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     *this method provide NewsDatabase instance in application scope
     *
     * @param context  ApplicationContext
     * @return  the database instance of the application, NewsDatabase
     **/
    @Singleton
    @Provides
    fun provideNewsDb(@ApplicationContext context: Context) : NewsDatabase {
        return  Room
            .databaseBuilder(
                context,
                NewsDatabase::class.java,
                NewsDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * this method provides the DAO instance of room database
     *
     * @param newsDatabase room database instance
     * @return the instance of NewsDao
     * */
    @Singleton
    @Provides
    fun provideNewsDAO(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }
}