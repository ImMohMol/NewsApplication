package com.example.newsapplication.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentBreakingNewsBinding
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreakingNewsFragment : Fragment() {
    private lateinit var binding: FragmentBreakingNewsBinding
    private val viewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_breaking_news, container, false)
        this.binding.lifecycleOwner = this
        return this.binding.root
    }
}