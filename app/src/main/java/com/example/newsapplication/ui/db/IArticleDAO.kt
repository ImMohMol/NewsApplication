package com.example.newsapplication.ui.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapplication.ui.model.Article

@Dao
interface IArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAndUpdate(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}