package com.example.newsapplication.ui.util

import android.app.Application
import com.example.newsapplication.ui.db.ArticleDatabase
import com.example.newsapplication.ui.repository.INewsRepository
import com.example.newsapplication.ui.repository.NewsRepository
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)

        val myModules = module {
            single { ArticleDatabase(this@App) }

            factory<INewsRepository> { NewsRepository(get()) }

            viewModel { NewsViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}