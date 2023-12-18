package id.my.nutrikita.di

import android.content.Context
import id.my.nutrikita.data.local.database.FavoriteFoodDatabase
import id.my.nutrikita.data.remote.retrofit.ApiConfig
import id.my.nutrikita.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val ccApiService = ApiConfig.getCCApiService()
        val mlApiService = ApiConfig.getMLApiService()
        val database = FavoriteFoodDatabase.getDatabase(context)
        val favoriteFoodDao = database.favoriteFoodDao()
        return Repository.getInstance(ccApiService, mlApiService, favoriteFoodDao)
    }
}