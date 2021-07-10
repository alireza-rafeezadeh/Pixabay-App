package com.app.core.data.repository.search

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchRepositoryModule {
    @Singleton
    @Binds
    abstract fun provideHomeRepository(repository : SearchRepositoryImpl) : SearchRepository
}