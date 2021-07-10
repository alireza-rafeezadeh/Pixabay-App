package com.app.core.data.network.api.search

import com.app.core.BuildConfig
import com.app.core.domain.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("api")
    suspend fun search(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("image_type") imageType: String
//        @Query("page") page: String
    ): Response<SearchResponse>
}