package id.my.nutrikita.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.nutrikita.data.local.entity.FoodData
import id.my.nutrikita.data.repository.Repository
import kotlinx.coroutines.launch

class FavoriteFoodViewModel(private val repository: Repository) : ViewModel() {
    fun deleteFavorite(name: String) {
        viewModelScope.launch {
            repository.deleteFavoriteFoodsById(name)
        }
    }

    fun getAllFavoriteFood(): LiveData<List<FoodData>> =
        repository.getFavoriteFoods()
}