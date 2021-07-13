package com.app.pixabay.framework.search

import com.app.core.data.datasource.BaseDataSource
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.data.network.api.search.SearchApi
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : BaseDataSource(), SearchRemoteDataSource {
    override suspend fun search(
        searchQuery: String, page: String
    ): Flow<ResultWrapper<SearchResponse>> {
        return flowOnIO {
            searchApi.search(q = searchQuery, imageType = "photo", page = page)
        }
    }

}