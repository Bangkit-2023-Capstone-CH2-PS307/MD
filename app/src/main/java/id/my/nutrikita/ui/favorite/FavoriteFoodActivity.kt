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
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}