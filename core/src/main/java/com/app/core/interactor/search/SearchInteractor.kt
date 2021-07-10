package com.app.core.interactor.search

import com.app.core.data.repository.search.SearchRepository
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {
    suspend fun searchImage() : Flow<ResultWrapper<SearchResponse>> {
        return searchRepository.search()
    }
}