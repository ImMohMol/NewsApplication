package com.example.newsapplication.ui.repository

import com.example.newsapplication.ui.model.NewsResponse
import retrofit2.Response

interface INewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>
}