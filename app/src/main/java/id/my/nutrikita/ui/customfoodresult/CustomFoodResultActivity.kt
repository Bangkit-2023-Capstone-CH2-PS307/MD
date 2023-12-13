package id.my.nutrikita.ui.customfoodresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.my.nutrikita.R
import id.my.nutrikita.databinding.ActivityCustomFoodResultBinding

class CustomFoodResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomFoodResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomFoodResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}