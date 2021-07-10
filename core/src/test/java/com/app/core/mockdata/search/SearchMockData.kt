package com.app.core.mockdata.search

import com.app.core.domain.search.SearchResponse

object SearchMockData {
    fun getSearchMockResponse() = SearchResponse(emptyList(),0,0)
}