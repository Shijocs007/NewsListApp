package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comments(@Expose @SerializedName("comments") val comments : Int)
