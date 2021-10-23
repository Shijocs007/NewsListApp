package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Likes(@Expose @SerializedName("likes") val likes : Int)
