package com.example.newsapplication.ui.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ActivityNewsBinding
import com.example.newsapplication.ui.ui.viewModel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNewsBinding
    private lateinit var navController: NavController
    private val viewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewsBinding.inflate(this.layoutInflater)
        setContentView(this.binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.newsNavHostFragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        this.binding.bottomNavigationView.setupWithNavController(navController)

        this.appBarConfiguration = AppBarConfiguration(
            setOf(R.id.breakingNewsFragment, R.id.savedNewsFragment, R.id.searchNewsFragment)
        )
        this.setupActionBarWithNavController(navController, this.appBarConfiguration)
    }
}