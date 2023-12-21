package id.my.nutrikita.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import id.my.nutrikita.R
import id.my.nutrikita.ViewModelFactory
import id.my.nutrikita.data.local.entity.FoodData
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.databinding.ActivityMainBinding
import id.my.nutrikita.ui.checkfoodnutrition.CheckFoodNutritionActivity
import id.my.nutrikita.ui.customfood.CustomFoodActivity
import id.my.nutrikita.ui.detailfood.DetailFoodActivity
import id.my.nutrikita.ui.favorite.FavoriteAdapter
import id.my.nutrikita.ui.favorite.FavoriteFoodActivity
import id.my.nutrikita.ui.login.LoginActivity
import id.my.nutrikita.ui.newsview.NewsViewActivity
import android.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        viewModel = obtainViewModel(this)

        setupView()
        setupFavoriteRecyclerView()
        setBindingView()
        setupNewsData()

        viewModel.getAllFavoriteFood().observe(this) {
            setupFavoriteFoodData(it)
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

    private fun setupFavoriteRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteFood.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavoriteFood.setHasFixedSize(true)
        binding.rvFavoriteFood.addItemDecoration(itemDecoration)
    }

    private fun setupFavoriteFoodData(foodData: List<FoodData>) {
        if (foodData.isNotEmpty()) {
            binding.tvEmptyFavorite.visibility = View.GONE
            binding.ivKitten.visibility = View.GONE
            binding.rvFavoriteFood.visibility = View.VISIBLE
            val foods = foodData.take(5)

            val adapter = FavoriteAdapter()
            adapter.submitList(foods)
            binding.rvFavoriteFood.adapter = adapter

            adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
                override fun onItemClicked(data: FoodData) {
                    val intent = Intent(this@MainActivity, DetailFoodActivity::class.java)
                    intent.putExtra(DetailFoodActivity.EXTRA_FAV_FOOD_DATA, data)
                    startActivity(intent)
                }

                override fun onFavoriteClicked(data: FoodData, btnFavorite: ImageButton) {
                    data.name?.let { viewModel.deleteFavorite(it) }
                }

            })
        } else {
            binding.tvEmptyFavorite.visibility = View.VISIBLE
            binding.rvFavoriteFood.visibility = View.GONE
            binding.ivKitten.visibility = View.VISIBLE
        }
    }

    private fun setupNewsData() {
        viewModel.getInsightData().observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    showLoading(false)
                    val listNews = result.data.data

                    Glide.with(this)
                        .load(listNews[0].image)
                        .into(binding.ivInsights1)
                    Glide.with(this)
                        .load(listNews[1].image)
                        .into(binding.ivInsights2)
                    Glide.with(this)
                        .load(listNews[2].image)
                        .into(binding.ivInsights3)
                    Glide.with(this)
                        .load(listNews[3].image)
                        .into(binding.ivInsights4)
                    Glide.with(this)
                        .load(listNews[4].image)
                        .into(binding.ivInsights5)

                    binding.tvInsightsTitle1.text = listNews[0].title
                    binding.tvInsightsTitle2.text = listNews[1].title
                    binding.tvInsightsTitle3.text = listNews[2].title
                    binding.tvInsightsTitle4.text = listNews[3].title
                    binding.tvInsightsTitle5.text = listNews[4].title

                    binding.tvInsightsTitle1.alpha = 1f
                    binding.tvInsightsTitle2.alpha = 1f
                    binding.tvInsightsTitle3.alpha = 1f
                    binding.tvInsightsTitle4.alpha = 1f
                    binding.tvInsightsTitle5.alpha = 1f

                    binding.cvInsights1.setOnClickListener {

                    }
                    binding.cvInsights2.setOnClickListener { intentNews(listNews[0].url) }
                    binding.cvInsights3.setOnClickListener { intentNews(listNews[1].url) }
                    binding.cvInsights4.setOnClickListener { intentNews(listNews[2].url) }
                    binding.cvInsights5.setOnClickListener { intentNews(listNews[3].url) }
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

    private fun intentNews(url: String) {
        val intent = Intent(this, NewsViewActivity::class.java)
        intent.putExtra(NewsViewActivity.EXTRA_NEWS_URL, url)
        startActivity(intent)
    }

    private fun setBindingView() {
        val user = auth.currentUser
        binding.ibLogout.setOnClickListener {
            showLogoutDialog()
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
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.logout))
        builder.setMessage(getString(R.string.logout_message))
        builder.setPositiveButton(getString(R.string.yes)) { _: DialogInterface, _: Int ->
            logout()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.setOnShowListener {
            alertDialog.findViewById<TextView>(android.R.id.message)?.setTextAppearance(
                R.style.DialogTextStyle
            )
            alertDialog.findViewById<TextView>(android.R.id.title)?.setTextAppearance(
                R.style.DialogTextStyle
            )
        }

        alertDialog.show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun logout() {
        Firebase.auth.signOut()
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }
}