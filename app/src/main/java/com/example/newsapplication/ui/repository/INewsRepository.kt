package com.example.newsapplication.ui.repository

import androidx.lifecycle.LiveData
import com.example.newsapplication.ui.model.Article
import com.example.newsapplication.ui.model.NewsResponse
import retrofit2.Response

interface INewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse>

    suspend fun insertAndUpdate(article: Article) : Long

    fun getFavoriteArticles(): LiveData<List<Article>>

    suspend fun deleteArticle(article: Article)
}