package com.app.pixabay.presentation.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import com.app.core.interactor.search.SearchInteractor
import com.app.core.interactor.search.SearchInteractors
import com.app.pixabay.presentation.ui.AppCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        viewModel.searchImage()
        SearchResponse(emptyList(), 0, 0).also { expectedResp ->
            assertThat(viewModel.searchImageLiveData.value)
                .isEqualTo(ResultWrapper.Success(expectedResp))
        }
    }
}