package id.my.nutrikita.ui.checkfoodresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.my.nutrikita.R
import id.my.nutrikita.databinding.ActivityCheckFoodResultBinding
import id.my.nutrikita.ui.checkfoodnutrition.CheckFoodNutritionActivity
import id.my.nutrikita.ui.main.MainActivity

class CheckFoodResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckFoodResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckFoodResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnArrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}