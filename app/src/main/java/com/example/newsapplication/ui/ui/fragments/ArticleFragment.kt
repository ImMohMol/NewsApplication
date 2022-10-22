package com.example.newsapplication.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentArticleBinding
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private val viewModel: NewsViewModel by viewModel()
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false)
        this.binding.lifecycleOwner = this
        return this.binding.root
    }

    override fun onResume() {
        super.onResume()
        this.displayArticleInWebView()
    }

    private fun displayArticleInWebView() {
        val article = this.args.article
        this.binding.webView.apply {
            this.webViewClient = WebViewClient()
            this.loadUrl(article.url)
        }
    }
}