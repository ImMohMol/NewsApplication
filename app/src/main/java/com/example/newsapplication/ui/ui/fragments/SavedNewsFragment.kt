package com.example.newsapplication.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentSavedNewsBinding
import com.example.newsapplication.ui.adapter.NewsAdapter
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_saved_news, container, false)
        this.binding.lifecycleOwner = this
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupRecyclerView()
        this.observeObservers()
    }

    private fun observeObservers() {

    }

    private fun setupRecyclerView() {
        this.newsAdapter = NewsAdapter()
        this.newsAdapter.setOnItemClickListener {
            this.findNavController().navigate(
                BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(it)
            )
        }
        this.binding.rvSavedNews.apply {
            this.adapter = newsAdapter
            this.layoutManager = LinearLayoutManager(activity)
        }
    }
}