package id.my.nutrikita.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.data.remote.response.FoodDetectResponse
import id.my.nutrikita.data.remote.response.NewsResponse
import id.my.nutrikita.data.repository.Repository
import okhttp3.MultipartBody

class MainViewModel(private val repository: Repository) : ViewModel() {
    fun getInsightData(
    ): LiveData<Result<NewsResponse>> =
        repository.getNews()
}