package com.app.core.data.repository.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.interactor.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

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


    //TODO: this test doesn't pass yet
    /*
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
    }*/
}