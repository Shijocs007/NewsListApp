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

    val newsList : LiveData<Resource<List<News>>> by lazy { repository.getMoviesList().asLiveData() }

}