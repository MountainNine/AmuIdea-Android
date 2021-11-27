package com.mtnine.amuidea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mtnine.amuidea.databinding.ItemIdeasBinding
import com.mtnine.amuidea.model.Post

class ItemAdapter() : Adapter<ItemAdapter.ItemViewHolder>() {
    var itemData = mutableListOf<Post>()

    class ItemViewHolder(val binding: ItemIdeasBinding) : ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.ideaPost = post
        }
    }

    fun setData(data: ArrayList<Post>) {
        itemData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemIdeasBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemData[position])
    }

    override fun getItemCount() = itemData.size
}