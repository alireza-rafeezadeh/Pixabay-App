package com.app.pixabay.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.Hit
import com.app.core.domain.search.SearchResponse
import com.app.core.interactor.search.SearchInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractors: SearchInteractors
) : ViewModel() {

    private var _searchImageLiveData = MutableLiveData<ResultWrapper<SearchResponse>>()
    val searchImageLiveData: LiveData<ResultWrapper<SearchResponse>> = _searchImageLiveData
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Hit>>? = null

    fun searchWithPaging(queryString: String): Flow<PagingData<Hit>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Hit>> =
            searchInteractors.searchInteractor.getSearchResultStream(queryString)
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}
