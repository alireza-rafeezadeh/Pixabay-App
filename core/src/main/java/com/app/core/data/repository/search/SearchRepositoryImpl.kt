package com.app.core.data.repository.search

import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun search(searchQuery: String): Flow<ResultWrapper<SearchResponse>> {
        return searchRemoteDataSource.search(searchQuery)
    }
}