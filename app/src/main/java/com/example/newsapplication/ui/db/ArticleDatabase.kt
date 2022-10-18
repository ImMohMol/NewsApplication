package com.example.newsapplication.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapplication.ui.model.Article

@Database(entities = [Article::class], version = 1)
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