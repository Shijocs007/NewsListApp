package com.example.newsapp.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.models.News
import com.example.newsapp.network.Resource
import com.example.newsapp.repository.NewsListrepository
import com.example.newsapp.utils.Enums
import com.example.newsapp.utils.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NewsListrepository
) : ViewModel() {

    var newsList = MutableLiveData<PagingData<News>>()
    var bookMarkedList = MutableLiveData<List<News>>()
    var state = MutableLiveData<Enums.PageState>()
    init {
        loadNewsList()
    }

    fun loadNewsList() {
        state.value = Enums.PageState.LOADING
        viewModelScope.launch {
            repository.getNewsList().cachedIn(viewModelScope).collect {
                newsList.value = it
                state.value = Enums.PageState.SUCCESS
            }
        }
    }

    fun getBookMarkedList() {
        bookMarkedList = repository.getBookMarkedList().asLiveData() as MutableLiveData<List<News>>
    }
}