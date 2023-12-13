package id.my.nutrikita.ui.customfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import id.my.nutrikita.R
import id.my.nutrikita.databinding.ActivityCustomFoodBinding
import id.my.nutrikita.ui.main.MainActivity

class CustomFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBindingView()
    }

    private fun setupBindingView() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.btnGetMealsSugesstion.setOnClickListener {

        }

        initializeSeekBar(binding.sbCalories, binding.quantityCalories, R.string.calories_quantity)
        initializeSeekBar(binding.sbFat, binding.quantityFat, R.string.fat_quantity)
        initializeSeekBar(binding.sbSaturatedFat, binding.quantitySaturatedFat, R.string.saturatedfat_quantity)
        initializeSeekBar(binding.sbCholestrol, binding.quantityCholestrol, R.string.cholestrol_quantity)
        initializeSeekBar(binding.sbSodium, binding.quantitySodium, R.string.sodium_quantity)
        initializeSeekBar(binding.sbCarbohydrate, binding.quantityCarbohydrate, R.string.carbohydrate_quantity)
        initializeSeekBar(binding.sbFiber, binding.quantityFiber, R.string.fiber_quantity)
        initializeSeekBar(binding.sbSugar, binding.quantitySugar, R.string.sugar_quantity)
        initializeSeekBar(binding.sbProtein, binding.quantityProtein, R.string.protein_quantity)
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