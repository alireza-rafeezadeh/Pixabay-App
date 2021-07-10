package com.app.core.data.repository.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.ResultWrapper
import com.app.core.interactor.MainCoroutineRule
import com.app.core.mockdata.search.SearchMockData
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchRepositoryImplTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {
        searchRepository = SearchRepositoryImpl(FakeSearchRemoteDataSource())
    }

    @Test
    fun search() = runBlockingTest {
        val expectedResponse = SearchMockData.getSearchMockResponse()
        searchRepository.search()
            .collect {
                Truth.assertThat(it)
                    .isEqualTo(
                        ResultWrapper.Success(expectedResponse)
                    )
            }
    }
}