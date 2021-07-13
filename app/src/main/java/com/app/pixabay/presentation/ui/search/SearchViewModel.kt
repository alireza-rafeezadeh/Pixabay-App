package com.app.pixabay.presentation.ui.search

import android.util.Log
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractors: SearchInteractors
) : ViewModel() {

    private var _searchImageLiveData = MutableLiveData<ResultWrapper<SearchResponse>>()
    val searchImageLiveData: LiveData<ResultWrapper<SearchResponse>> = _searchImageLiveData

    fun searchImage(searchQuery: String) = viewModelScope.launch {
        searchInteractors.searchInteractor.searchImage(searchQuery)
//            .catch {
//                _searchImageLiveData.postValue(ResultWrapper.ErrorString(it.message ?: ""))
//            }
            .transform {
                if (it is ResultWrapper.Success && it.data.totalHits == 0) {
                    emit(ResultWrapper.ErrorString("No Item Found!"))
                } else {
                    emit(it)
                }
            }
            .collect {
//                Log.i("search_resp_t", "searchImage: $it")
                _searchImageLiveData.postValue(it)
            }
    }

    private var currentQueryValue: String? = null

    var currentSearchResult: Flow<PagingData<Hit>>? = null


    fun searchWithPaging(queryString: String): Flow<PagingData<Hit>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString

        val newResult: Flow<PagingData<Hit>> =
            searchInteractors.searchInteractor.getSearchResultStream(queryString)
                .catch {
                    Log.i("s_w_p_exception", "searchWithPaging: " + it.message)
                }
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}



