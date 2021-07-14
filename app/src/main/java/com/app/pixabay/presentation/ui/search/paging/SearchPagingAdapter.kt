package com.app.pixabay.presentation.ui.search.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.search.Hit
import com.app.pixabay.databinding.ItemSearchBinding
import com.bumptech.glide.Glide

class SearchPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<Hit>,
    val onClick: (item: Hit) -> Unit
) :
    PagingDataAdapter<Hit, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let {
            HitViewHolder(it)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as HitViewHolder).bindView(it)
        }
    }

    inner class HitViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { item -> onClick(item) }
            }
        }

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
