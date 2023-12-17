package id.my.nutrikita.ui.main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import id.my.nutrikita.R
import id.my.nutrikita.ViewModelFactory
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.databinding.ActivityMainBinding
import id.my.nutrikita.ui.checkfoodnutrition.CheckFoodNutritionActivity
import id.my.nutrikita.ui.customfood.CustomFoodActivity
import id.my.nutrikita.ui.customfoodresult.CustomFoodResultActivity
import id.my.nutrikita.ui.favorite.FavoriteFoodActivity
import id.my.nutrikita.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        mainViewModel = obtainViewModel(this)

        setupView()
        setBindingView()
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setBindingView() {
        val user = auth.currentUser
        binding.ibLogout.setOnClickListener{
            logout()
        }
        binding.tvHomeTitle.text = getString(R.string.home_title, user?.displayName)
        binding.cvCustomFood.setOnClickListener {
            startActivity(Intent(this, CustomFoodActivity::class.java))
        }
        binding.btnFavoritePage.setOnClickListener {
            startActivity(Intent(this, FavoriteFoodActivity::class.java))
        }
        binding.cvCheckFoodNutrition.setOnClickListener {
            startActivity(Intent(this, CheckFoodNutritionActivity::class.java))
        }

        mainViewModel.getInsightData().observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    showLoading(false)
                    val listNews = result.data.data

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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun logout(){
        Firebase.auth.signOut()
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }


}