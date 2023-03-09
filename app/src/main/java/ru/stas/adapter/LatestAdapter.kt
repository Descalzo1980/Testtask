package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.stas.model.FlashSale
import ru.stas.model.LatestX
import ru.stas.testtask.databinding.ListItemLatestBinding

class LatestAdapter : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    private var latestList = listOf<LatestX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        return LatestViewHolder(ListItemLatestBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        holder.bind(latestList[position])

    }

    override fun getItemCount() = latestList.size

    inner class LatestViewHolder(private val binding: ListItemLatestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(latest: LatestX) {
            binding.tvPhonesCategory.text = latest.category
            binding.tvPhoneName.text = latest.name
            binding.tvPrice.text = latest.price.toString()
            Glide.with(binding.ivLatestPhoto)
                .load(latest.image_url)
                .centerCrop()
                .transform(RoundedCorners(9))
                .into(binding.ivLatestPhoto)
        }
    }

    fun setFlashList(newList: List<LatestX>) {
        val diffResult = DiffUtil.calculateDiff(LatestDiffCallback(latestList, newList)
        )
        latestList = newList
        diffResult.dispatchUpdatesTo(this)
    }
    private class LatestDiffCallback(private val oldList: List<LatestX>, private val newList: List<LatestX>) : DiffUtil.Callback() {
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
