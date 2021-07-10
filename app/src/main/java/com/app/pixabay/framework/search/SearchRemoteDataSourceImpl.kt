package com.app.pixabay.framework.search

import com.app.core.data.datasource.BaseDataSource
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor() : BaseDataSource(), SearchRemoteDataSource {
    override fun search(): Flow<ResultWrapper<SearchResponse>> {
        return flowOf(ResultWrapper.ErrorString(""))
    }
}