package com.app.core.mockdata.search

import com.app.core.domain.search.Hit
import com.app.core.domain.search.SearchResponse

object SearchMockData {
    fun getSearchMockResponse() = SearchResponse(getLongSearchList(), 0, 0)

    fun getSearchList() = listOf(
        Hit(
            0, 1, 123, 12, 100, "testUrl",
            200, "previewUrl", "user", "user", 500
        )
    )


    fun getLongSearchList() = listOf(
        Hit(
            0, 1, 123, 12, 100, "testUrl",
            200, "previewUrl", "user", "user", 500
        ),
        Hit(
            0, 1, 123, 12, 100, "testUrl",
            200, "previewUrl", "user", "user", 500
        )
    )
}