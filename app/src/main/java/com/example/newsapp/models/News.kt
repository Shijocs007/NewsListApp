package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class News (
	@Expose @SerializedName("author") val author : String?,
	@Expose @SerializedName("title") val title : String,
	@Expose @SerializedName("description") val description : String?,
	@Expose @PrimaryKey(autoGenerate = false) @SerializedName("url") val url : String,
	@Expose @SerializedName("urlToImage") val urlToImage : String?,
	@Expose @SerializedName("publishedAt") val publishedAt : String?,
	@Expose @SerializedName("content") val content : String?,
	var bookmark : Boolean = false
)