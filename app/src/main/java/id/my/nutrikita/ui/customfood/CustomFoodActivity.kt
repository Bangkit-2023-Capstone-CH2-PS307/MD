package id.my.nutrikita.ui.customfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import id.my.nutrikita.R
import id.my.nutrikita.ViewModelFactory
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.databinding.ActivityCustomFoodBinding
import id.my.nutrikita.ui.checkfoodnutrition.CheckFoodNutritionActivity
import id.my.nutrikita.ui.checkfoodnutrition.CheckFoodNutritionViewModel
import id.my.nutrikita.ui.checkfoodresult.CheckFoodResultActivity
import id.my.nutrikita.ui.customfoodresult.CustomFoodResultActivity
import id.my.nutrikita.ui.main.MainActivity

class CustomFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomFoodBinding
    private lateinit var viewModel: CustomFoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this)

        setupBindingView()
    }

    private fun obtainViewModel(activity: CustomFoodActivity): CustomFoodViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[CustomFoodViewModel::class.java]
    }

    private fun setupBindingView() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnGetMealsSugesstion.setOnClickListener {
            seekbarEnabled(false)

            val caloriesValue = binding.sbCalories.progress
            val fatValue = binding.sbFat.progress
            val saturatedFatValue = binding.sbSaturatedFat.progress
            val cholesterolValue = binding.sbCholesterol.progress
            val sodiumValue = binding.sbSodium.progress
            val carbohydrateValue = binding.sbCarbohydrate.progress
            val fiberValue = binding.sbFiber.progress
            val sugarValue = binding.sbSugar.progress
            val proteinValue = binding.sbProtein.progress

            viewModel.postCustomFood(caloriesValue, fatValue, saturatedFatValue, cholesterolValue, sodiumValue, carbohydrateValue, fiberValue, sugarValue, proteinValue).observe(this) { result ->
                when (result) {
                    is Result.Success -> {
                        showLoading(false)
                        seekbarEnabled(true)
                        val intent = Intent(this, CustomFoodResultActivity::class.java)
                        intent.putExtra(CustomFoodResultActivity.EXTRA_FOOD_DATA, ArrayList(result.data))
                        startActivity(intent)
                    }

                    is Result.Error -> {
                        showLoading(false)
                    }

                    is Result.Empty -> {
                        showLoading(false)
                    }

                    is Result.Loading -> {
                        showLoading(true)
                    }
                }
            }
        }

        initializeSeekBar(binding.sbCalories, binding.quantityCalories, R.string.calories_quantity)
        initializeSeekBar(binding.sbFat, binding.quantityFat, R.string.fat_quantity)
        initializeSeekBar(
            binding.sbSaturatedFat,
            binding.quantitySaturatedFat,
            R.string.saturatedfat_quantity
        )
        initializeSeekBar(
            binding.sbCholesterol,
            binding.quantityCholesterol,
            R.string.cholesterol_quantity
        )
        initializeSeekBar(binding.sbSodium, binding.quantitySodium, R.string.sodium_quantity)
        initializeSeekBar(
            binding.sbCarbohydrate,
            binding.quantityCarbohydrate,
            R.string.carbohydrate_quantity
        )
        initializeSeekBar(binding.sbFiber, binding.quantityFiber, R.string.fiber_quantity)
        initializeSeekBar(binding.sbSugar, binding.quantitySugar, R.string.sugar_quantity)
        initializeSeekBar(binding.sbProtein, binding.quantityProtein, R.string.protein_quantity)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun seekbarEnabled(isEnabled : Boolean) {
        binding.sbCalories.isEnabled = isEnabled
        binding.sbFat.isEnabled = isEnabled
        binding.sbSaturatedFat.isEnabled = isEnabled
        binding.sbCholesterol.isEnabled = isEnabled
        binding.sbSodium.isEnabled = isEnabled
        binding.sbCarbohydrate.isEnabled = isEnabled
        binding.sbFiber.isEnabled = isEnabled
        binding.sbSugar.isEnabled = isEnabled
        binding.sbProtein.isEnabled = isEnabled
    }

    private fun initializeSeekBar(seekBar: SeekBar, textView: TextView, stringResId: Int) {
        textView.text = getString(stringResId, "0")
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = getString(stringResId, progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Implement logic when tracking starts
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Implement logic when tracking stops
            }
        })
    }
}