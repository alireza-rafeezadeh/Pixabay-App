package com.app.pixabay.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import com.app.core.interactor.search.SearchInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractors: SearchInteractors
) : ViewModel() {

    private var _searchImageLiveData= MutableLiveData<ResultWrapper<SearchResponse>>()
    val searchImageLiveData: LiveData<ResultWrapper<SearchResponse>> = _searchImageLiveData

    fun searchImage() = viewModelScope.launch {
        searchInteractors.searchInteractor.searchImage()
            .collect {
                _searchImageLiveData.postValue(it)
            }
    }
}