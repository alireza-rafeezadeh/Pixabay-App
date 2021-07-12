package com.app.pixabay.mockdata

import com.app.core.domain.search.Hit
import com.app.core.domain.search.SearchResponse

object SearchMockData {
    fun getSearchMockResponse() = SearchResponse(emptyList(), 0, 0)

    fun getFilledSearchMockResponse(): SearchResponse {
        val hit = Hit(
            0, 1, 123, 12, 100, "testUrl",
            200, "previewUrl", "user", "user", 500
        )
        return SearchResponse(listOf(hit, hit), 2, 2)
    }

    fun getSearchList() = item


    val item =listOf(
    Hit(
    0, 1, 123, 12, 100, "testUrl",
    200, "previewUrl", "user", "user", 500
    )
    )

}