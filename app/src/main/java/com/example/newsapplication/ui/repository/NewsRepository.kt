package com.example.newsapplication.ui.repository

import com.example.newsapplication.ui.api.RetrofitInstance
import com.example.newsapplication.ui.db.ArticleDatabase
import com.example.newsapplication.ui.model.NewsResponse
import retrofit2.Response

class NewsRepository(private val db: ArticleDatabase) : INewsRepository {
    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> = RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)
}