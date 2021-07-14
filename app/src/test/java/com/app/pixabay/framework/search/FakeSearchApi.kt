package com.app.pixabay.framework.search

import com.app.core.data.network.api.search.SearchApi
import com.app.core.domain.search.SearchResponse
import com.app.pixabay.mockdata.SearchMockData
import retrofit2.Response

class FakeSearchApi : SearchApi {
    override suspend fun search(
        key: String,
        q: String,
        imageType: String,
        page: String
    ): Response<SearchResponse> {
        return Response.success(SearchMockData.getSearchMockResponse())
    }
}
