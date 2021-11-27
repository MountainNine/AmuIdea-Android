package com.mtnine.amuidea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mtnine.amuidea.databinding.ItemIdeasBinding
import com.mtnine.amuidea.vm.Item

class ItemAdapter() : Adapter<ItemAdapter.ItemViewHolder>() {
    var items = ArrayList<Item>()
        set(value) {
            items.clear()
            items.addAll(value)
        }

    class ItemViewHolder(val binding: ItemIdeasBinding) : ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.ideaItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemIdeasBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}