package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey(autoGenerate = false) val id : Int,
    val name : String
    )
