package com.app.core.data.repository.search

import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow


interface SearchRepository {
    suspend fun search() : Flow<ResultWrapper<SearchResponse>>
}