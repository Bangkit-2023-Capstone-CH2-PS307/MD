package id.my.nutrikita.di

import android.content.Context
import id.my.nutrikita.data.remote.retrofit.ApiConfig
import id.my.nutrikita.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}