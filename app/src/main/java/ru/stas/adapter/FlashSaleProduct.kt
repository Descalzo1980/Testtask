package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.stas.model.FlashSale
import ru.stas.model.LatestX
import ru.stas.testtask.databinding.ListItemFlashBinding

class FlashSaleAdapter() :
    RecyclerView.Adapter<FlashSaleAdapter.ViewHolder>() {

    private var flashSales = listOf<FlashSale>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemFlashBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(flashSales[position])
    }

    override fun getItemCount(): Int {
        return flashSales.size
    }

//    fun submitList(newList: List<FlashSale>) {
//        val diffResult = DiffUtil.calculateDiff(LatestDiffCallback(flashSales, newList)
//        )
//        flashSales = newList
//        diffResult.dispatchUpdatesTo(this)
//    }
    fun setFlashList(flashSale: List<FlashSale>){
        this.flashSales = ArrayList(flashSale)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemFlashBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flashSale: FlashSale) {
            binding.tvCategoryFlash.text = flashSale.category
            binding.tvCategoryNameFlash.text = flashSale.name
            binding.tvPriceFlash.text = flashSale.price.toString()
            Glide.with(binding.root)
                .load(flashSale.image_url)
                .into(binding.ivPhotoFlash)
        }
    }

    private class LatestDiffCallback(private val oldList: List<FlashSale>, private val newList: List<FlashSale>) : DiffUtil.Callback() {
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
