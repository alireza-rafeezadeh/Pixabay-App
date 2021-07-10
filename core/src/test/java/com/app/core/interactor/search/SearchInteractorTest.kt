package com.app.core.interactor.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.ResultWrapper
import com.app.core.interactor.MainCoroutineRule
import com.app.core.mockdata.search.SearchMockData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SearchInteractorTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var searchInteractor: SearchInteractor

    @Before
    fun setUp() {
        searchInteractor = SearchInteractor(FakeSearchRepository())
    }

    @Test
    fun searchImage() = runBlockingTest {
        val expectedResponse = SearchMockData.getSearchMockResponse()
        searchInteractor.searchImage()
            .collect {
                assertThat(it)
                    .isEqualTo(
                        ResultWrapper.Success(expectedResponse)
                    )
            }
    }
}