package com.example.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.utils.Enums
import com.example.newsapp.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class NewsFragment : Fragment() {

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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )
        initObservers()
        return binding.root
    }

    private fun initObservers() {


        binding.apply {

            shimmerView.startShimmer()
            shimmerView.visibility = View.VISIBLE
            noConnection.setOnClickListener {
                viewModel.loadNewsList()
            }

            recyclerView.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(context)
            }

            viewModel.newsList.observe(viewLifecycleOwner) { result ->
                newsAdapter.submitData(viewLifecycleOwner.lifecycle, result)
            }

            viewModel.state.observe(viewLifecycleOwner) { state ->
                when (state) {
                    Enums.PageState.LOADING -> {
                        shimmerView.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        shimmerView.startShimmer()
                    }
                    Enums.PageState.SUCCESS -> {
                        shimmerView.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        shimmerView.stopShimmer()
                    }
                }
            }
        }
    }

}