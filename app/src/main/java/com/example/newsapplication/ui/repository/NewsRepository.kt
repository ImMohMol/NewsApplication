package com.example.newsapplication.ui.repository

import androidx.lifecycle.LiveData
import com.example.newsapplication.ui.api.RetrofitInstance
import com.example.newsapplication.ui.db.ArticleDatabase
import com.example.newsapplication.ui.model.Article
import com.example.newsapplication.ui.model.NewsResponse
import retrofit2.Response

class NewsRepository(private val db: ArticleDatabase) : INewsRepository {
    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> = RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)

    override suspend fun insertAndUpdate(article: Article): Long =
        this.db.getArticleDAO().insertAndUpdate(article)

    override fun getFavoriteArticles(): LiveData<List<Article>> =
        this.db.getArticleDAO().getArticles()

    override suspend fun deleteArticle(article: Article) =
        this.db.getArticleDAO().deleteArticle(article)
}