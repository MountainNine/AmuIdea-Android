package com.mtnine.amuidea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mtnine.amuidea.databinding.ItemIdeasBinding
import com.mtnine.amuidea.vm.Item

class ItemAdapter() : Adapter<ItemAdapter.ItemViewHolder>() {
    var items = ArrayList<Item>()
    var itemData = MutableLiveData<ArrayList<Item>>()

    init {
        itemData.value = items
    }

    class ItemViewHolder(val binding: ItemIdeasBinding) : ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.ideaItem = item
        }
    }

    fun setData(data: ArrayList<Item>) {
        itemData.value = data
        notifyDataSetChanged()
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