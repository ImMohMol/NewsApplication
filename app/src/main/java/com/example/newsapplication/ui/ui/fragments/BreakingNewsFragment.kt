package com.example.newsapplication.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentBreakingNewsBinding
import com.example.newsapplication.ui.adapter.NewsAdapter
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import com.example.newsapplication.ui.util.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BreakingNewsFragment : Fragment() {
    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_breaking_news, container, false)
        this.binding.lifecycleOwner = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupRecyclerView()
        this.observeObservers()
    }

    private fun observeObservers() {
        this.viewModel.breakingNewsLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    this.hideProgressbar()
                    response.data?.let { newsResponse ->
                        this.newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    this.hideProgressbar()
                    response.message?.let { errorMessage ->
                        Timber.e(errorMessage)
                    }
                }
                is Resource.Loading -> {
                    this.showProgressbar()
                }
            }
        }
    }

    private fun hideProgressbar() {
        this.binding.paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressbar() {
        this.binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        this.binding.rvBreakingNews.apply {
            this.adapter = newsAdapter
            this.layoutManager = LinearLayoutManager(activity)
        }
    }
}