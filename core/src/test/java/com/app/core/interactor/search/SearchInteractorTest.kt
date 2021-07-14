package com.app.core.interactor.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.interactor.MainCoroutineRule
import com.app.core.mockdata.search.SearchMockData
import com.app.core.util.collectData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
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
    fun `search image should return success`() = runBlockingTest {
        searchInteractor.getSearchResultStream("text to search")
            .collectLatest { pagingData ->
                pagingData.collectData()
                    .also { list ->
                        assertThat(list)
                            .isEqualTo(SearchMockData.getSearchList())
                    }
            }
    }
}