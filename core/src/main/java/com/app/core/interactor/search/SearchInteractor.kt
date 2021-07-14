package com.app.core.interactor.search

import androidx.paging.PagingData
import com.app.core.data.repository.search.SearchRepository
import com.app.core.domain.search.Hit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {
    fun getSearchResultStream(query: String): Flow<PagingData<Hit>> {
        return searchRepository.getSearchResultStream(query)
    }
}