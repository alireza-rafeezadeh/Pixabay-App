package com.app.core.data.network.api

import com.app.core.data.network.api.search.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchServiceModule {
    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}