package com.app.core.data.repository.search

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.ResultWrapper
import com.app.core.interactor.MainCoroutineRule
import com.app.core.mockdata.search.SearchMockData
import com.app.core.util.collectData
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
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

    //todo: remove this function
    @Test
    fun search() = runBlockingTest {
        val expectedResponse = SearchMockData.getSearchMockResponse()
        searchRepository.search("text to search")
            .collect {
                Truth.assertThat(it)
                    .isEqualTo(
                        ResultWrapper.Success(expectedResponse)
                    )
            }
    }

    @Test
    fun `should search with pagination return success`() = runBlocking {
        val expectedResponse = SearchMockData.getSearchMockResponse()
        searchRepository.getSearchResultStream("text to search")
            .catch {
                Log.i("<<TAG>>", "should search with pagination return success: ")
            }
            .collectLatest { pagingData ->
//                Truth.assertThat(pagingData)
//                    .isEqualTo(
//                        ResultWrapper.Success(expectedResponse)
//                    )
//
                pagingData.collectData()
                    .also { list ->
                        Truth.assertThat(list)
                            .isEqualTo(SearchMockData.getSearchList())
                    }
            }
    }
}