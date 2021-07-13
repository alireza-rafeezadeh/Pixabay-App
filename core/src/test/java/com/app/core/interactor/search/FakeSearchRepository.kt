package com.app.core.interactor.search

import androidx.paging.PagingData
import com.app.core.data.repository.search.SearchRepository
import com.app.core.domain.search.Hit
import com.app.core.mockdata.search.SearchMockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakeSearchRepository : SearchRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<Hit>> {
        return flowOf(PagingData.from(SearchMockData.getSearchList()))
    }
}