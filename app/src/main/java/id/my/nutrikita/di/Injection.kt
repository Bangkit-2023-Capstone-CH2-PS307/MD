package id.my.nutrikita.di

import android.content.Context
import id.my.nutrikita.data.remote.retrofit.ApiConfig
import id.my.nutrikita.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val ccApiService = ApiConfig.getCCApiService()
        val mlApiService = ApiConfig.getMLApiService()
        return Repository.getInstance(ccApiService, mlApiService)
    }
}