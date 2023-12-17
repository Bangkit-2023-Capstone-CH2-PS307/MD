package id.my.nutrikita.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.my.nutrikita.R
import id.my.nutrikita.databinding.ActivityFavoriteFoodBinding
import id.my.nutrikita.ui.main.MainActivity

class FavoriteFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}