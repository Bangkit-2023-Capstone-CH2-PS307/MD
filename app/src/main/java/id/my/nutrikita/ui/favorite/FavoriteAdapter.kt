package id.my.nutrikita.ui.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.my.nutrikita.data.local.entity.FoodData
import id.my.nutrikita.databinding.ItemResultBinding

class FavoriteAdapter:
    ListAdapter<FoodData, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK)  {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class FavoriteViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: FoodData, clickListener: OnItemClickCallback) {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        TODO("Not yet implemented")
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