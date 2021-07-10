package com.app.pixabay.presentation.ui.search

import androidx.lifecycle.ViewModel
import com.app.core.interactor.search.SearchInteractors
import javax.inject.Inject


class SearchViewModel @Inject constructor(val searchInteractors: SearchInteractors) : ViewModel() {

}