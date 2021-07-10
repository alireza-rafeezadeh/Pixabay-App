package com.app.pixabay.framework.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.ResultWrapper
import com.app.pixabay.mockdata.SearchMockData
import com.app.pixabay.presentation.ui.AppCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchRemoteDataSourceImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = AppCoroutineRule()

    lateinit var searchRemoteDataSource: SearchRemoteDataSourceImpl

    @Before
    fun setUp() {
        searchRemoteDataSource = SearchRemoteDataSourceImpl(FakeSearchApi())
    }

    @Test
    fun search2() = runBlocking {
        searchRemoteDataSource.search()
            .collect {
                assertThat(it)
                    .isEqualTo(
                        ResultWrapper.Success(
                            SearchMockData.getSearchMockResponse()
                        )
                    )
            }
    }
}