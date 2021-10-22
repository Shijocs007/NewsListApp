package com.example.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsDetailsBinding
import com.example.newsapp.databinding.ActivityNewsListBinding
import com.example.newsapp.models.News
import com.google.gson.GsonBuilder

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initViews()
    }

    private fun initViews() {
        val data = intent.getStringExtra("data")
        data.let {
            val news = GsonBuilder().create().fromJson(it, News::class.java)
            if(news != null){
                binding.apply {
                    title.text = news.title
                    author.text = news.author
                    date.text = news.publishedAt
                    content.text = news.content
                    Glide.with(content)
                        .load(news.urlToImage)
                        .placeholder(R.drawable.image_place_holder)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(image)
                }
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            setSupportActionBar(this)
            supportActionBar?.title = "News Details"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }
    }
}