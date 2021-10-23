package com.example.newsapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.News
import com.example.newsapp.network.NewsApi
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception


private const val UNSPLASH_STARTING_PAGE_INDEX = 1
val POST_PER_PAGE = 20

class NewsListPagingSource(
    private val api: NewsApi, private val dao : NewsDao
) : PagingSource<Int, News>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            var news: List<News>? = null
            try {
                val response = api.getNewsList(position)
                news = response.articles
                dao.upsertAll(news!!)
            } catch (e : Exception) {
                news = dao.getNews(POST_PER_PAGE, position)
            }
            LoadResult.Page(
                data = news!!,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (news.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
