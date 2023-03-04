package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.stas.model.LatestX
import ru.stas.testtask.databinding.ListItemLatestBinding

class LatestAdapter(private var latest: List<LatestX>): RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        return LatestViewHolder(ListItemLatestBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        holder.bind(latest[position])
    }

    override fun getItemCount() = latest.size

    fun setItems(latest: List<LatestX>?) {
        this.latest = latest ?: emptyList()
        notifyDataSetChanged()
    }

    inner class LatestViewHolder(private val binding: ListItemLatestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(latest: LatestX) {
            binding.tvPhonesCategory.text = latest.category
            binding.tvPhoneName.text = latest.name
            binding.tvPrice.text = latest.price.toString()
            Glide.with(binding.root)
                .load(latest.image_url)
                .into(binding.ivLatestPhoto)
        }
    }
}