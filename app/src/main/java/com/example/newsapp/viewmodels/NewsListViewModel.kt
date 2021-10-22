package com.example.newsapp.viewmodels

import androidx.lifecycle.*
import com.example.newsapp.models.News
import com.example.newsapp.network.Resource
import com.example.newsapp.repository.NewsListrepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NewsListrepository
) : ViewModel() {

    lateinit var newsList : LiveData<Resource<List<News>>>
    var bookMarkedList = MutableLiveData<List<News>>()

    init {
        loadNewsList()
    }

    fun loadNewsList() {
        newsList = repository.getMoviesList().asLiveData()
    }

    fun getBookMarkedList() {
        bookMarkedList = repository.getBookMarkedList().asLiveData() as MutableLiveData<List<News>>
    }

}