package com.example.newsapplication.ui.db

import android.content.Context
import androidx.room.*
import com.example.newsapplication.ui.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDAO(): IArticleDAO

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "articles_db.db"
        ).build()
    }
}