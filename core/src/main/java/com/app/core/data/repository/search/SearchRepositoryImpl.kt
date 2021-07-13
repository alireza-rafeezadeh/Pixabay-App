package com.app.core.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.search.Hit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(query, searchRemoteDataSource) }
        ).flow
    }
}