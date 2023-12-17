package id.my.nutrikita.ui.detailfood

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import id.my.nutrikita.R
import id.my.nutrikita.data.remote.response.CustomFoodResponseItem
import id.my.nutrikita.databinding.ActivityDetailFoodBinding

class DetailFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFoodData()
        binding.btnArrowBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupFoodData() {
        val foodData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FOOD_DATA, CustomFoodResponseItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FOOD_DATA)
        }

        val listSteps = foodData?.recipeInstructions?.split(".,")?.map { it.trim() + "." }

        binding.tvFoodName.text = foodData?.name
        binding.tvDescription.text = foodData?.description
        binding.tvIngredientsContent.text = textListBuilder(foodData?.recipeIngredientParts)
        binding.tvStepsContent.text = textListBuilder(listSteps)
        Glide.with(this)
            .load(foodData?.images)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_place_holder)
            .into(binding.ivFoodResult)

    }

    fun textListBuilder(list: List<String>?): SpannableStringBuilder {
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
    }
}