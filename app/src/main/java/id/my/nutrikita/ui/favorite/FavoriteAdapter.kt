package id.my.nutrikita.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.nutrikita.R
import id.my.nutrikita.data.local.entity.FoodData
import id.my.nutrikita.databinding.FavoriteItemBinding
import id.my.nutrikita.databinding.ItemResultBinding

class FavoriteAdapter:
    ListAdapter<FoodData, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK)  {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class FavoriteViewHolder(private val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: FoodData, clickListener: OnItemClickCallback) {
            binding.tvFoodName.text = result.name
            Glide.with(binding.ivFavoriteFood.context)
                .load(result.images)
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(binding.ivFavoriteFood)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result, onItemClickCallback)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FoodData)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodData>() {
            override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
                return oldItem == newItem
            }
        }
    }
}