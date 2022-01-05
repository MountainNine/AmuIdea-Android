package com.mtnine.amuidea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.JsonObject
import com.mtnine.amuidea.databinding.ItemIdeasBinding
import com.mtnine.amuidea.model.Post

class ItemAdapter() : Adapter<ItemAdapter.ItemViewHolder>() {
    var itemData = mutableListOf<JsonObject>()

    class ItemViewHolder(val binding: ItemIdeasBinding) : ViewHolder(binding.root) {
        fun bind(post: JsonObject) {
            val date =post["StartDate"].asString
            val words = post["Words"].asString
            val idea = post["Idea"].asString
            binding.ideaPost = Post(null, date, words, idea)
        }
    }

    fun setData(data: ArrayList<JsonObject>) {
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