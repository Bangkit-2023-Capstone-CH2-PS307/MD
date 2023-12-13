package id.my.nutrikita.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.my.nutrikita.data.local.entity.FoodData

@Dao
interface FavoriteFoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodData: FoodData)

    @Query("DELETE FROM food_data WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("SELECT * from food_data")
    fun getAllFavorites(): LiveData<List<FoodData>>

    @Query("SELECT * FROM food_data WHERE id=:id")
    fun getFavoriteFoodById(id: Int): LiveData<FoodData>
}