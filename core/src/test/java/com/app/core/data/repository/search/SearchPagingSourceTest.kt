package com.app.core.data.repository.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.interactor.MainCoroutineRule
import com.app.core.mockdata.search.SearchMockData
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchPagingSourceTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var searchRemoteSDataSource: SearchRemoteDataSource
    private val mockResponse = SearchMockData.getLongSearchList()

    @Before
    fun setUp() {
        searchRemoteSDataSource = FakeSearchRemoteDataSource()
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlockingTest {
        val pagingSource = SearchPagingSource("text to search", searchRemoteSDataSource)

        Truth.assertThat(pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )).isEqualTo(PagingSource.LoadResult.Page(
            data = listOf(mockResponse[0], mockResponse[1]),
            prevKey = null,
            nextKey = 2
        ))
    }
}