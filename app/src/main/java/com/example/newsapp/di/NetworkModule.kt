package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.network.LikesApi
import com.example.newsapp.network.NetworkConnectionIntercepter
import com.example.newsapp.network.NewsApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://newsapi.org/"
    const val LIKES_BASE_URL = "https://cn-news-info-api.herokuapp.com/"

    /**
     * provides Gson for retrofit
     *
     * @return Gson for retrofit converter factory
     * */
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    /**
     * provide network interceptor for connection debugging
     *
     * @param context applicationContext
     * @return
     * */
    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context) : NetworkConnectionIntercepter {
        return NetworkConnectionIntercepter(context)
    }

    /**
     * provide okhttp client for retrofit
     *
     * @param networkConnectionIntercepter http interceptor
     * @return OkHttpClient
     * */
    @Singleton
    @Provides
    fun provideOkhttp(networkConnectionIntercepter: NetworkConnectionIntercepter) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionIntercepter)
            .build()
    }

    /**
     * provide retrofit builder
     *
     * @param gson for json to object convert
     * @param okHttpClient
     * @return retrofit instance
     * */
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit.Builder): NewsApi {
        return retrofit
            .build()
            .create(NewsApi::class.java)
    }

    @Named("LikesApi")
    @Singleton
    @Provides
    fun provideLikeRetrofit(gson: Gson, okHttpClient: OkHttpClient) :  Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(LIKES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideLikesService(@Named("LikesApi") retrofit: Retrofit.Builder): LikesApi {
        return retrofit
            .build()
            .create(LikesApi::class.java)
    }
}