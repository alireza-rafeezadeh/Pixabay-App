package com.app.core.mockdata.search

import com.app.core.domain.search.Hit
import com.app.core.domain.search.SearchResponse

object SearchMockData {
    fun getSearchMockResponse() = SearchResponse(emptyList(), 0, 0)

    fun getSearchList() = listOf(
        Hit(
            0, 1, 123, 12, 100, "testUrl",
            200, "previewUrl", "user", "user", 500
        )
    )
}