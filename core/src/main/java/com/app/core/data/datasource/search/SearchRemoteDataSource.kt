package com.app.core.data.datasource.search

import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    fun search() : Flow<ResultWrapper<List<String>>>
}