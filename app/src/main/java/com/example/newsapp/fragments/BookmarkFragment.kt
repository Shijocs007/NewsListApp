package com.example.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.network.Resource
import com.example.newsapp.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private val viewModel: NewsListViewModel by activityViewModels()
    private lateinit var binding: FragmentNewsBinding
    val newsAdapter = NewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )
        viewModel.getBookMarkedList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {

        binding.apply {

            noConnection.setOnClickListener {
                viewModel.loadNewsList()
            }

            recyclerView.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(context)
            }

            viewModel.bookMarkedList.observe(viewLifecycleOwner) { result ->
                newsAdapter.submitList(result)
                recyclerView.isVisible = result.isNotEmpty()
                shimmerView.isVisible = result.isEmpty()
            }
        }


    }
}