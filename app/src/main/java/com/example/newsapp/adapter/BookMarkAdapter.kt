package com.example.newsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsapp.R
import com.example.newsapp.activities.NewsDetailsActivity
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.models.News
import com.google.gson.Gson

class BookMarkAdapter : ListAdapter<News, BookMarkAdapter.NewsViewHolder>(NewsComparator())  {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.apply {
                Glide.with(itemView)
                    .load(news.urlToImage)
                    .placeholder(R.drawable.image_place_holder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(image)

                title.text = news.title
                author.text = news.author
                date.text = news.publishedAt

                itemView.setOnClickListener{
                    itemView.context.startActivity(
                        Intent( itemView.context, NewsDetailsActivity::class.java)
                        .putExtra("data", Gson().toJson(news)))
                }
            }
        }
    }

    class NewsComparator : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: News, newItem: News) =
            oldItem == newItem
    }
}