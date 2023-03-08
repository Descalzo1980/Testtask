package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.stas.model.FlashSale
import ru.stas.testtask.databinding.ListItemFlashBinding

class FlashSaleAdapter() : RecyclerView.Adapter<FlashSaleAdapter.ViewHolder>() {

    private var flashSales = listOf<FlashSale>()

    interface OnItemClickListener {
        fun onItemClick(flashSale: FlashSale)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemFlashBinding.inflate(inflater,parent,false)
        return ViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(flashSales[position])
    }

    override fun getItemCount(): Int {
        return flashSales.size
    }


    inner class ViewHolder(private val binding: ListItemFlashBinding,listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemFlashSale.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val flashSale = flashSales[position]
                    onItemClickListener.onItemClick(flashSale)
                }
            }
        }
        fun bind(flashSale: FlashSale) {
            binding.tvCategoryFlash.text = flashSale.category
            binding.tvCategoryNameFlash.text = flashSale.name
            binding.tvPriceFlash.text = flashSale.price.toString()
            binding.tvDiscountFlash.text = "${flashSale.discount}% off"
            Glide.with(binding.root)
                .load(flashSale.image_url)
                .centerCrop()
                .transform(RoundedCorners(9))
                .override(174,221)
                .into(binding.ivPhotoFlash)
        }
    }

    fun submitList(newList: List<FlashSale>) {
        val diffResult = DiffUtil.calculateDiff(LatestDiffCallback(flashSales, newList)
        )
        flashSales = newList
        diffResult.dispatchUpdatesTo(this)
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


