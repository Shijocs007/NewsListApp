package com.example.newsapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.repository.NewsListrepository
import com.example.newsapp.repository.NewsdetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewmodel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NewsdetailsRepository
) : ViewModel() {



    fun setBookMarked(bookMarked: Boolean, id : String) {
        viewModelScope.launch {
            repository.setBookMarked(bookMarked, id)
        }
    }


}