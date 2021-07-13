package com.app.core.data.repository.search

import androidx.paging.PagingData
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.Hit
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow


interface SearchRepository {
    suspend fun search(searchQuery : String) : Flow<ResultWrapper<SearchResponse>>
    fun getSearchResultStream(query: String): Flow<PagingData<Hit>>
}