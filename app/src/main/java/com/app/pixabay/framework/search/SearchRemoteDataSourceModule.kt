package com.app.pixabay.framework.search

import com.app.core.data.datasource.search.SearchRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchRemoteDataSourceModule {
    @Singleton
    @Binds
    abstract fun providesSearchRemoteDataSource(
        searchRemoteDataSource: SearchRemoteDataSourceImpl
    ): SearchRemoteDataSource
}