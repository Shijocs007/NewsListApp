package com.example.newsapp.di

import com.example.newsapp.db.NewsDao
import com.example.newsapp.network.NewsApi
import com.example.newsapp.repository.NewsListrepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {


    /**
     * provide movie repository class
     *
     * @param api NewsApi instance where we written all api requests
     * @param dao to get the data form local db
     * @return repository module @link{NewsListrepository}
     * */
    @Provides
    fun provideMovieRepository(api: NewsApi, dao: NewsDao) : NewsListrepository {
        return NewsListrepository(
            api,
            dao
        )
    }
}