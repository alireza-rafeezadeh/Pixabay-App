package com.app.pixabay.presentation.ui.search.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.search.Hit
import com.app.pixabay.databinding.ItemSearchBinding
import com.bumptech.glide.Glide


class SearchPagingAdapter(diffCallback: DiffUtil.ItemCallback<Hit>) :
    PagingDataAdapter<Hit, RecyclerView.ViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)
        item?.let {
            (holder as HitViewHolder).bindView(it)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HitViewHolder(binding)
    }


    inner class HitViewHolder(private val binding: ItemSearchBinding) :
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

object SearchComparator : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem
    }
}