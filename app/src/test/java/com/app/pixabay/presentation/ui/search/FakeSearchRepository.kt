package com.app.pixabay.presentation.ui.search

import androidx.paging.PagingData
import com.app.core.data.repository.search.SearchRepository
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.Hit
import com.app.pixabay.mockdata.SearchMockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakeSearchRepository : SearchRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<Hit>> {
        return flowOf(PagingData.from(SearchMockData.getSearchList()))
//        flowOf(PagingData.empty())
    }

    override suspend fun search(searchQuery: String) = flowOf(
        ResultWrapper.Success(
            SearchMockData.getFilledSearchMockResponse()
        )
    )
}