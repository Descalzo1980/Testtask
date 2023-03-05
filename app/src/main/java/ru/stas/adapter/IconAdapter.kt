package ru.stas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.stas.model.Icons
import ru.stas.testtask.databinding.ListItemIconsBinding

class IconAdapter(private var icons: List<Icons>):
RecyclerView.Adapter<IconAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemIconsBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(icons[position])
    }

    override fun getItemCount() = icons.size

    inner class ViewHolder(private val binding: ListItemIconsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(icons: Icons){
            binding.name.text = icons.name
            binding.image.setImageResource(icons.drawableId)
        }
    }
}