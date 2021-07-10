package com.app.pixabay.presentation.ui.search

import com.app.core.data.repository.search.SearchRepository
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakeSearchRepository : SearchRepository {
    override fun search(): Flow<ResultWrapper<SearchResponse>> {
        return flowOf(ResultWrapper.Success(SearchResponse(emptyList(),0,0)))
    }
}