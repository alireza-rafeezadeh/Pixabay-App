package com.app.core.data.datasource.search

import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    fun search() : Flow<ResultWrapper<SearchResponse>>
}