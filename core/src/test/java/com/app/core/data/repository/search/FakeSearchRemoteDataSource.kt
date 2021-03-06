package com.app.core.data.repository.search

import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import com.app.core.mockdata.search.SearchMockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSearchRemoteDataSource : SearchRemoteDataSource {
    override suspend fun search(
        searchQuery: String,
        page: String
    ): Flow<ResultWrapper<SearchResponse>> =
        flowOf(ResultWrapper.Success(SearchMockData.getSearchMockResponse()))
}