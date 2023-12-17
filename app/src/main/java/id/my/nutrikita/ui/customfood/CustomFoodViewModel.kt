package id.my.nutrikita.ui.customfood

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.data.remote.response.CustomFoodResponseItem
import id.my.nutrikita.data.repository.Repository

class CustomFoodViewModel(private val repository: Repository) : ViewModel() {
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
    ): LiveData<Result<List<CustomFoodResponseItem>>> =
        repository.postCustomFood(
            calories,
            fatContent,
            saturatedFatContent,
            cholesterolContent,
            sodiumContent,
            carbohydrateContent,
            fiberContent,
            sugarContent,
            proteinContent
        )
}