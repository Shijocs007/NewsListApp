package com.example.newsapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.repository.NewsListrepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NewsListrepository
) : ViewModel() {

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
           var result =  repository.getMoviesList().body()
            print("shijo")
        }
    }

}