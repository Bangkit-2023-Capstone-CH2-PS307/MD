package id.my.nutrikita.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import id.my.nutrikita.data.remote.model.RegisterModel
import id.my.nutrikita.data.remote.response.RegisterResponse
import id.my.nutrikita.data.remote.retrofit.CCApiService
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.data.remote.response.CustomFoodResponse
import id.my.nutrikita.data.remote.response.ErrorResponse
import id.my.nutrikita.data.remote.response.FoodDetectResponse
import id.my.nutrikita.data.remote.response.NewsResponse
import id.my.nutrikita.data.remote.retrofit.MLApiService
import okhttp3.MultipartBody
import retrofit2.HttpException

class Repository private constructor(
    private val ccApiService: CCApiService,
    private val mlApiService: MLApiService
) {
    fun postRegister(registerData: RegisterModel): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response =
                ccApiService.register(registerData.name, registerData.email, registerData.password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            Log.d(Repository::class.java.simpleName, "postRegister: ${e.message.toString()}")
            emit(Result.Error(errorMessage))
        }
    }

    fun getNews(): LiveData<Result<NewsResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = ccApiService.getNews()
            if (response.data.isNotEmpty()) {
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postFoodDetect(
        multipartBody: MultipartBody.Part,
    ): LiveData<Result<FoodDetectResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = mlApiService.foodDetect(multipartBody)
            emit(Result.Success(response))
        } catch (e: HttpException) {
//            val jsonInString = e.response()?.errorBody()?.string()
//            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
//            val errorMessage = errorBody.message
//            Log.d(Repository::class.java.simpleName, "postRegister: ${e.message.toString()}")
//            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun postCustomFood(
        calories: Int,
        fatContent: Int,
        saturatedFatContent: Int,
        cholesterolContent: Int,
        sodiumContent: Int,
        carbohydrateContent: Int,
        fiberContent: Int,
        sugarContent: Int,
        proteinContent: Int
    ): LiveData<Result<CustomFoodResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = mlApiService.customFood(calories, fatContent, saturatedFatContent, cholesterolContent, sodiumContent, carbohydrateContent, fiberContent, sugarContent, proteinContent)
            emit(Result.Success(response))
        } catch (e: HttpException) {

        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            ccApiService: CCApiService,
            mlApiService: MLApiService
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(ccApiService, mlApiService)
        }.also { instance = it }
    }
}