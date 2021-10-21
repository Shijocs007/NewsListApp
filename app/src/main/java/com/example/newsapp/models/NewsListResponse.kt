package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsListResponse (

	@Expose @SerializedName("status") val status : String?,
	@Expose @SerializedName("totalResults") val totalResults : Int?,
	@Expose @SerializedName("articles") val articles : List<News>?,
	@Expose @SerializedName("code") val code : String?,
	@Expose @SerializedName("message") val message : String?
)