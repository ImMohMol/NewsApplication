package com.example.newsapplication.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentSearchNewsBinding
import com.example.newsapplication.ui.adapter.NewsAdapter
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import com.example.newsapplication.ui.util.Constants
import com.example.newsapplication.ui.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchNewsFragment : Fragment() {
    private lateinit var binding: FragmentSearchNewsBinding
    private val viewModel: NewsViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_news, container, false)
        this.binding.lifecycleOwner = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.observeObservers()
        this.createSearchStuff()
        this.setupRecyclerView()
    }

    private fun observeObservers() {
        this.viewModel.searchNewsLiveData.observe(viewLifecycleOwner) { response ->
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

    private fun createSearchStuff() {
        var searchJob: Job? = null
        this.binding.etSearch.addTextChangedListener { editable ->
            searchJob?.cancel()
            searchJob = MainScope().launch {
                delay(Constants.SEARCH_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
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
        this.binding.rvSearchNews.apply {
            this.adapter = newsAdapter
            this.layoutManager = LinearLayoutManager(activity)
        }
    }
}