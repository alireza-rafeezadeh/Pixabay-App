package com.app.pixabay.presentation.ui.search.paging

import androidx.recyclerview.widget.DiffUtil
import com.app.core.domain.search.Hit

object SearchComparator : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean = oldItem == newItem
}
