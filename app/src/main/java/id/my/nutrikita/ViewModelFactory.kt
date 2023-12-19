package id.my.nutrikita

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.my.nutrikita.data.repository.Repository
import id.my.nutrikita.di.Injection
import id.my.nutrikita.ui.checkfoodnutrition.CheckFoodNutritionViewModel
import id.my.nutrikita.ui.customfood.CustomFoodViewModel
import id.my.nutrikita.ui.customfoodresult.CustomFoodResultViewModel
import id.my.nutrikita.ui.detailfood.DetailFoodViewModel
import id.my.nutrikita.ui.favorite.FavoriteFoodViewModel
import id.my.nutrikita.ui.main.MainViewModel
import id.my.nutrikita.ui.register.RegisterViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CheckFoodNutritionViewModel::class.java)) {
            return CheckFoodNutritionViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CustomFoodViewModel::class.java)) {
            return CustomFoodViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CustomFoodResultViewModel::class.java)) {
            return CustomFoodResultViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(DetailFoodViewModel::class.java)) {
            return DetailFoodViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(FavoriteFoodViewModel::class.java)) {
            return FavoriteFoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}