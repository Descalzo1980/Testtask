package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.stas.model.FlashSale
import ru.stas.testtask.databinding.ListItemFlashBinding

class FlashSaleAdapter(private var flashSales: List<FlashSale>) :
    RecyclerView.Adapter<FlashSaleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemFlashBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flashSale = flashSales[position]
        holder.bind(flashSale)
    }

    override fun getItemCount(): Int {
        return flashSales.size
    }

    fun setItems(flashSales: List<FlashSale>?) {
        this.flashSales = flashSales ?: emptyList()
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
}
