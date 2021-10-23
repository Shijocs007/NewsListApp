package com.example.newsapp.di

import com.example.newsapp.db.NewsDao
import com.example.newsapp.network.LikesApi
import com.example.newsapp.network.NewsApi
import com.example.newsapp.repository.NewsListPagingSource
import com.example.newsapp.repository.NewsListrepository
import com.example.newsapp.repository.NewsdetailsRepository
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
     * provide news details repository class
     *
     * @param api NewsApi instance where we written all api requests
     * @param dao to get the data form local db
     * @return repository module @link{NewsdetailsRepository}
     * */
    @Provides
    fun provideNewsDetailsRepository(api: LikesApi, dao: NewsDao) : NewsdetailsRepository {
        return NewsdetailsRepository(
            api,
            dao
        )
    }

    /**
     * provide NewsListPagingSource  class
     *
     * @param api NewsApi instance where we written all api requests
     * @param dao to get the data form local db
     * @return repository module @link{NewsListPagingSource}
     * */
    @Provides
    fun provideNewsListDatasource(api: NewsApi, dao: NewsDao) : NewsListPagingSource {
        return NewsListPagingSource(
            api,
            dao
        )
    }

    /**
     * provide news repository class
     *
     * @param api NewsApi instance where we written all api requests
     * @param dao to get the data form local db
     * @return repository module @link{NewsListrepository}
     * */
    @Provides
    fun provideNewsRepository(api: NewsApi, dao: NewsDao, source: NewsListPagingSource) : NewsListrepository {
        return NewsListrepository(
            api,
            dao,
            source
        )
    }
}