package com.example.newsapplication.ui.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.ui.model.Article
import com.example.newsapplication.ui.model.NewsResponse
import com.example.newsapplication.ui.repository.INewsRepository
import com.example.newsapplication.ui.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository: INewsRepository) : ViewModel() {
    private var breakingNewsPage = 1
    val breakingNewsLiveData = MutableLiveData<Resource<NewsResponse>>()

    private var searchNewsPage = 1
    val searchNewsLiveData = MutableLiveData<Resource<NewsResponse>>()

    init {
        getBreakingNews()
    }

    private fun getBreakingNews(countryCode: String = "us") = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val breakingNewsResponse = repository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNewsLiveData.postValue(handleBreakingNewsResponse(breakingNewsResponse))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val searchNewsResponse = repository.searchNews(searchQuery, searchNewsPage)
        searchNewsLiveData.postValue(handleSearchNewsResponse(searchNewsResponse))
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.insertAndUpdate(article)
    }

    fun getFavoriteArticles() = this.repository.getFavoriteArticles()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        repository.deleteArticle(article)
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}