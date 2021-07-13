package com.app.core.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.Hit
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun search(searchQuery: String): Flow<ResultWrapper<SearchResponse>> {
        return searchRemoteDataSource.search(searchQuery,"1")
    }

    override fun getSearchResultStream(query: String): Flow<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(query, searchRemoteDataSource) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}