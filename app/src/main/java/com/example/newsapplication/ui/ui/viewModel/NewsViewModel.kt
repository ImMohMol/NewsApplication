package com.example.newsapplication.ui.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.ui.model.NewsResponse
import com.example.newsapplication.ui.repository.INewsRepository
import com.example.newsapplication.ui.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository: INewsRepository) : ViewModel() {
    private var breakingNewsPage = 1
    val breakingNewsLiveData = MutableLiveData<Resource<NewsResponse>>()

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val breakingNewsResponse = repository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNewsLiveData.postValue(handleBreakingNewsResponse(breakingNewsResponse))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}