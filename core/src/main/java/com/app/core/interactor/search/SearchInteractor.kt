package com.app.core.interactor.search

import com.app.core.data.repository.search.SearchRepository
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class SearchInteractor @Inject constructor(searchRepository: SearchRepository) {
    fun searchImage() : Flow<ResultWrapper<SearchResponse>> {
        return flowOf(ResultWrapper.ErrorString("error!"))
    }
}