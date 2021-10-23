package com.example.newsapp

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.newsapp.databinding.ActivityNewsListBinding
import com.example.newsapp.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {

    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var binding : ActivityNewsListBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)

        initToolbar()

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_news -> navController.navigate(R.id.nav_news_fragment)
                R.id.nav_bookmarks -> navController.navigate(R.id.nav_bookmark_fragment)
            }
            true
        }

    }

    private fun initToolbar() {
        binding.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_menu)
                navigationIcon!!
                    .setColorFilter(resources.getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP)
                setTitleTextColor(resources.getColor(R.color.grey_80))
                setSupportActionBar(this)
                supportActionBar!!.title = "News"
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

}