package ru.stas.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import ru.stas.model.Latest
import ru.stas.model.LatestX
import ru.stas.testtask.R
import ru.stas.testtask.databinding.ListItemLatestBinding
const val TAG = "LatestAdapter"
class LatestAdapter : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    private var latestList = mutableListOf<LatestX>()
    fun setFlashList(latestList1: List<LatestX>) {
        latestList.clear()
        latestList.addAll(latestList1)
        notifyDataSetChanged()
        Log.d(TAG, latestList.joinToString())
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
                .into(binding.ivLatestPhoto)
            binding.ivLatestPhoto.load(latest.image_url){
                placeholder(R.drawable.bill_up_close)
            }
        }
    }

}
