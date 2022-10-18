package com.example.newsapplication.ui.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.newsapplication.ui.repository.INewsRepository

class NewsViewModel(private val repository: INewsRepository) : ViewModel() {
}