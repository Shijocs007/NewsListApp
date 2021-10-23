package com.example.newsapp.viewmodels

import androidx.lifecycle.*
import com.example.newsapp.models.LikeAndComment
import com.example.newsapp.models.Likes
import com.example.newsapp.models.News
import com.example.newsapp.repository.NewsListrepository
import com.example.newsapp.repository.NewsdetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewmodel  @Inject constructor( private val repository: NewsdetailsRepository
) : ViewModel() {

    var likeAndComment = MutableLiveData<LikeAndComment>()


    fun getLikesAndComments(news : News) {
       val url = getUrlId(news.url)
        viewModelScope.launch {
            repository.getLikes(news.url).zip(repository.getComments(news.url)) {likes, comments ->
                LikeAndComment(likes = likes.likes, comments = comments.comments)
            }.collect {
                likeAndComment.value = it
            }
        }
    }


    fun setBookMarked(bookMarked: Boolean, id : String) {
        viewModelScope.launch {
            repository.setBookMarked(bookMarked, id)
        }
    }

    public fun getUrlId(url: String): Any {
        var url = url.replace("https://", "")
        return url.replace("/", "-")
    }

}