package com.app.core.data.repository.search

import androidx.paging.PagingData
import com.app.core.domain.search.Hit
import kotlinx.coroutines.flow.Flow


interface SearchRepository {
    fun getSearchResultStream(query: String): Flow<PagingData<Hit>>
}