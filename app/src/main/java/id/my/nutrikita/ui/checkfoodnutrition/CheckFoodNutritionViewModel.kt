package id.my.nutrikita.ui.checkfoodnutrition

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.data.remote.response.FoodDetectResponse
import id.my.nutrikita.data.repository.Repository
import okhttp3.MultipartBody

class CheckFoodNutritionViewModel(private val repository: Repository) : ViewModel() {
    fun postFoodDetect(
        multipartBody: MultipartBody.Part,
    ): LiveData<Result<FoodDetectResponse>> =
        repository.postFoodDetect(multipartBody)
}