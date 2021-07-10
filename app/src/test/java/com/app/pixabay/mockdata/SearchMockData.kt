package com.app.pixabay.mockdata

import com.app.core.domain.search.SearchResponse

object SearchMockData {
    fun getSearchMockResponse() = SearchResponse(emptyList(),0,0)
}