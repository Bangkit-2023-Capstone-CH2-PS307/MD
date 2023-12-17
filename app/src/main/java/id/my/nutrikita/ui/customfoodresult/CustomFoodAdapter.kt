package id.my.nutrikita.ui.customfoodresult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.nutrikita.R
import id.my.nutrikita.data.remote.response.CustomFoodResponseItem
import id.my.nutrikita.databinding.ItemResultBinding

class CustomFoodAdapter :
    ListAdapter<CustomFoodResponseItem, CustomFoodAdapter.CustomFoodViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class CustomFoodViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: CustomFoodResponseItem, clickListener: OnItemClickCallback) {
            binding.tvFoodName.text = result.name
            Glide.with(binding.cvResultItem.context)
                .load(result.images)
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(binding.ivFoodResult)
            binding.cvResultItem.setOnClickListener {
                clickListener.onItemClicked(result)
            }
            binding.ibFavorite.setOnClickListener {
                clickListener.onFavoriteClicked(result)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomFoodViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomFoodViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result, onItemClickCallback)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CustomFoodResponseItem)
        fun onFavoriteClicked(data: CustomFoodResponseItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CustomFoodResponseItem>() {
            override fun areItemsTheSame(
                oldItem: CustomFoodResponseItem,
                newItem: CustomFoodResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CustomFoodResponseItem,
                newItem: CustomFoodResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}