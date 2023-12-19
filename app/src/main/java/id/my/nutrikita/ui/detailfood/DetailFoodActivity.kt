package id.my.nutrikita.ui.detailfood

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import id.my.nutrikita.R
import id.my.nutrikita.ViewModelFactory
import id.my.nutrikita.data.local.entity.FoodData
import id.my.nutrikita.data.remote.response.CustomFoodResponseItem
import id.my.nutrikita.databinding.ActivityDetailFoodBinding

class DetailFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFoodBinding
    private lateinit var viewModel: DetailFoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this)

        binding.btnArrowBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val foodData: CustomFoodResponseItem? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FOOD_DATA, CustomFoodResponseItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FOOD_DATA)
        }

        val favFoodData: FoodData? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAV_FOOD_DATA, FoodData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAV_FOOD_DATA)
        }

        if (foodData != null) setupFoodData(foodData) else favFoodData?.let { setupFavData(it) }

    }

    private fun setupFoodData(foodData: CustomFoodResponseItem) {
        val listSteps = foodData.recipeInstructions?.split(".,")?.map { it.trim() + "." }
        var isFavorite = false
        foodData.name?.let { name ->
            viewModel.isFoodFavorite(name).observe(this) {
                isFavorite = it > 0
                if (isFavorite) {
                    binding.btnFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.btnFavorite.context,
                            R.drawable.baseline_favorite_24
                        )
                    )
                } else {
                    binding.btnFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.btnFavorite.context,
                            R.drawable.baseline_favorite_border_24
                        )
                    )
                }
            }
        }

        val favData = FoodData(
            name = foodData.name,
            description = foodData.description,
            recipeIngredientParts = foodData.recipeIngredientParts,
            recipeIngredientQuantities = foodData.recipeIngredientQuantities,
            images = foodData.images,
            recipeInstructions = foodData.recipeInstructions,
            recipeServings = foodData.recipeServings,
            recipeCategory = foodData.recipeCategory,
            totalTime = foodData.totalTime,
            sugarContent = foodData.sugarContent,
            cholesterolContent = foodData.cholesterolContent,
            saturatedFatContent = foodData.saturatedFatContent,
            proteinContent = foodData.proteinContent,
            sodiumContent = foodData.sodiumContent,
            calories = foodData.calories,
            carbohydrateContent = foodData.carbohydrateContent,
            fatContent = foodData.fatContent,
            fiberContent = foodData.fiberContent
        )

        binding.tvFoodName.text = foodData.name
        binding.tvDescription.text = foodData.description
        binding.tvIngredientsContent.text = textListBuilder(foodData.recipeIngredientParts)
        binding.tvStepsContent.text = textListBuilder(listSteps)
        Glide.with(this)
            .load(foodData.images)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_place_holder)
            .into(binding.ivFoodResult)

        binding.btnFavorite.setOnClickListener {
            if (isFavorite) {
                foodData.name?.let { name -> viewModel.deleteFavorite(name) }
            } else {
                viewModel.saveFavoriteFood(favData)
            }
        }
    }

    private fun setupFavData(favData: FoodData) {
        val listSteps = favData.recipeInstructions?.split(".,")?.map { it.trim() + "." }
        var isFavorite = false
        favData.name?.let { name ->
            viewModel.isFoodFavorite(name).observe(this) {
                isFavorite = it > 0
                if (isFavorite) {
                    binding.btnFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.btnFavorite.context,
                            R.drawable.baseline_favorite_24
                        )
                    )
                } else {
                    binding.btnFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.btnFavorite.context,
                            R.drawable.baseline_favorite_border_24
                        )
                    )
                }
            }
        }

        binding.tvFoodName.text = favData.name
        binding.tvDescription.text = favData.description
        binding.tvIngredientsContent.text = textListBuilder(favData.recipeIngredientParts)
        binding.tvStepsContent.text = textListBuilder(listSteps)
        Glide.with(this)
            .load(favData.images)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_place_holder)
            .into(binding.ivFoodResult)

        binding.btnFavorite.setOnClickListener {
            if (isFavorite) {
                favData.name?.let { name -> viewModel.deleteFavorite(name) }
            } else {
                viewModel.saveFavoriteFood(favData)
            }
        }
    }

    private fun obtainViewModel(activity: DetailFoodActivity): DetailFoodViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailFoodViewModel::class.java]
    }

    private fun textListBuilder(list: List<String>?): SpannableStringBuilder {
        val builder = SpannableStringBuilder()

        if (list != null) {
            for (item in list) {
                val formattedText = "• $item\n"
                builder.append(formattedText)
            }
        }
        return builder
    }

    companion object {
        const val EXTRA_FOOD_DATA = "extra_food"
        const val EXTRA_FAV_FOOD_DATA = "extra_fav_food"
    }
}