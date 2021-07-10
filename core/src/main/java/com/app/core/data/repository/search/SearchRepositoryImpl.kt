package com.app.core.data.repository.search

import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepository {
    override fun search(): Flow<ResultWrapper<SearchResponse>> {
        return flowOf(ResultWrapper.ErrorString(""))
    }
}