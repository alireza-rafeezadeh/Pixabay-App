package com.app.pixabay.presentation.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import com.app.core.domain.ResultWrapper
import com.app.core.interactor.search.SearchInteractor
import com.app.core.interactor.search.SearchInteractors
import com.app.pixabay.mockdata.SearchMockData
import com.app.pixabay.presentation.ui.AppCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = AppCoroutineRule()

    lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(SearchInteractors(SearchInteractor(FakeSearchRepository())))
    }

    @Test
    fun `search image should return success`() {
        viewModel.searchImage("test text")
//        val expectedResponse = SearchMockData.getFilledSearchMockResponse()
        SearchMockData.getFilledSearchMockResponse().also { expectedResp ->
            assertThat(viewModel.searchImageLiveData.value)
                .isEqualTo(ResultWrapper.Success(expectedResp))
        }
    }


    @Test
    fun `search image with paging should return success`() = runBlocking {
        viewModel.searchWithPaging("test text")
            .collectLatest { pagingData ->
                pagingData.collectData()
                    .also { list ->
                    assertThat(list)
                        .isEqualTo(SearchMockData.getSearchList())
                }
            }
    }
}



// todo: https://stackoverflow.com/questions/66503911/unit-testing-a-repository-with-paging-3-using-a-a-remote-mediator-and-paging-sou/66686920#66686920
@ExperimentalCoroutinesApi
suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            newCombinedLoadStates: CombinedLoadStates,
            lastAccessedIndex: Int,
            // todo: https://stackoverflow.com/questions/63522656/what-is-the-correct-way-to-check-the-data-from-a-pagingdata-object-in-android-un
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}