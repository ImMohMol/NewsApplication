package com.example.newsapplication.ui.repository

import com.example.newsapplication.ui.db.ArticleDatabase

class NewsRepository(private val db: ArticleDatabase) : INewsRepository {
}