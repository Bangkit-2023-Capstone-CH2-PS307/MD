package id.my.nutrikita.ui.customfoodresult

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import id.my.nutrikita.data.remote.response.CustomFoodResponseItem
import id.my.nutrikita.databinding.ActivityCustomFoodResultBinding
import id.my.nutrikita.ui.detailfood.DetailFoodActivity

class CustomFoodResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomFoodResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomFoodResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        setFoodsData()

        binding.btnArrowBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setRecyclerView() {
        val layoutManger = GridLayoutManager(this, 2)
        binding.rvFoodResultItem.layoutManager = layoutManger
        binding.rvFoodResultItem.setHasFixedSize(true)
    }

    private fun setFoodsData() {
        val adapter = CustomFoodAdapter()
        binding.rvFoodResultItem.adapter = adapter

        val foodData =  if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableArrayListExtra(EXTRA_FOOD_DATA, CustomFoodResponseItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra(EXTRA_FOOD_DATA)
        }

        adapter.submitList(foodData)

        adapter.setOnItemClickCallback(object : CustomFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CustomFoodResponseItem) {
                val intent = Intent(this@CustomFoodResultActivity, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.EXTRA_FOOD_DATA, data)
                startActivity(intent)
            }

            override fun onFavoriteClicked(data: CustomFoodResponseItem) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        const val EXTRA_FOOD_DATA = "food_data"
    }
}