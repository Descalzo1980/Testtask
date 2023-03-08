package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.stas.model.LatestX
import ru.stas.testtask.databinding.ListItemLatestBinding

class LatestAdapter : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    private var latestList = mutableListOf<LatestX>()
    fun setFlashList(latestList1: List<LatestX>) {
        latestList.clear()
        latestList.addAll(latestList1)
        notifyDataSetChanged()
    }
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
}
