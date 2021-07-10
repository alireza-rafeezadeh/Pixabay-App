package com.app.pixabay.framework.search

import com.app.core.data.datasource.BaseDataSource
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor() : BaseDataSource(), SearchRemoteDataSource {
    override fun search(): Flow<ResultWrapper<List<String>>> {
        return flowOf(ResultWrapper.ErrorString(""))
    }
}