package com.example.newsapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsDetailsBinding
import com.example.newsapp.databinding.ActivityNewsListBinding
import com.example.newsapp.models.News
import com.example.newsapp.utils.BookMarkLottieview
import com.example.newsapp.viewmodels.NewsDetailsViewmodel
import com.example.newsapp.viewmodels.NewsListViewModel
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsActivity : AppCompatActivity() {

    private val viewModel: NewsDetailsViewmodel by viewModels()
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
            var news = GsonBuilder().create().fromJson(it, News::class.java)
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
                    bookmark.setAnimation("lotte_bookmark_light.json")
                    setLotteState(bookmark as LottieAnimationView,news.bookmark)

                    bookmark.setOnClickListener {

                        bookmark.apply {
                            if (!news.bookmark) {
                                setBookMarkState(BookMarkLottieview.ITEM_BOOKMARKED)
                                setAnimationListener(object : BookMarkLottieview.BookMarkLottieAnimationListener{
                                    override fun onAnimationStarted() {
                                    }

                                    override fun onAnimationEnd() {
                                        removeAllAnimatorListeners()
                                        viewModel.setBookMarked(true, news.url)
                                        news.bookmark = true
                                    }
                                })
                            } else {
                                setBookMarkState(BookMarkLottieview.ITEM_UNBOOKMARKED)
                                setAnimationListener(object : BookMarkLottieview.BookMarkLottieAnimationListener{
                                    override fun onAnimationStarted() {

                                    }

                                    override fun onAnimationEnd() {
                                        removeAllAnimatorListeners()
                                        viewModel.setBookMarked(false, news.url)
                                        news.bookmark = false
                                    }
                                })
                            }
                            playAnimation()
                        }
                    }
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

    fun setLotteState(lotteView: LottieAnimationView, state: Boolean) {
        if(state){
            lotteView.setMinAndMaxFrame(0,62)
            lotteView.frame = 62
        }else{
            lotteView.setMinAndMaxFrame(70,126)
            lotteView.frame = 126
        }
    }
}