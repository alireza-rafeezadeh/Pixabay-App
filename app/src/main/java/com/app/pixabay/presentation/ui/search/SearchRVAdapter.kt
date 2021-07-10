package com.app.pixabay.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.search.Hit
import com.app.pixabay.databinding.ItemSearchBinding
import com.bumptech.glide.Glide


class SearchRVAdapter(private val images: List<Hit>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindView(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ItemViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(hit: Hit) {
            with(binding) {
                Glide.with(binding.root).load(hit.previewURL).into(previewImageView)
                userTextView.text = hit.user
                viewsTextView.text = hit.views.toString()
                downloadsTextView.text = hit.downloads.toString()
                likesTextView.text = hit.likes.toString()
            }
        }
    }
}