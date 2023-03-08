package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.stas.model.FlashItem
import ru.stas.testtask.databinding.FragmentPageTwoBinding

class FlashSaleItemAdapter(): RecyclerView.Adapter<FlashSaleItemAdapter.ViewHolder>() {

    private var flashItem = listOf<FlashItem>()

    inner class ViewHolder(private val binding: FragmentPageTwoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(flashItem: FlashItem){
            binding.tvPriceItemFlash.text = flashItem.price.toString()
            binding.tvFeaturesItemFlash.text = flashItem.description
            binding.tvCategoryItemFlash.text = flashItem.name
            Glide.with(binding.root)
                .load(flashItem.image_urls)
                .centerCrop()
                .transform(RoundedCorners(9))
                .override(174,221)
                .into(binding.ivItemFlash)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentPageTwoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(flashItem[position])
    }

    override fun getItemCount() = flashItem.size

    fun setFlashItem(newList: List<FlashItem>) {
        val diffResult = DiffUtil.calculateDiff(LatestDiffCallback(flashItem, newList)
        )
        flashItem = newList
        diffResult.dispatchUpdatesTo(this)
    }
    private class LatestDiffCallback(private val oldList: List<FlashItem>, private val newList: List<FlashItem>) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}