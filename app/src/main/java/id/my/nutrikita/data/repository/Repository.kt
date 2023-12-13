package id.my.nutrikita.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import id.my.nutrikita.data.remote.model.RegisterModel
import id.my.nutrikita.data.remote.response.RegisterResponse
import id.my.nutrikita.data.remote.retrofit.ApiService
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.data.remote.response.ErrorResponse
import retrofit2.HttpException

class Repository private constructor(
    private val apiService: ApiService,
) {
    fun postRegister(registerData: RegisterModel): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response =
                apiService.register(registerData.name, registerData.email, registerData.password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            Log.d(Repository::class.java.simpleName, "postRegister: ${e.message.toString()}")
            emit(Result.Error(errorMessage))
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(apiService)
        }.also { instance = it }
    }
}