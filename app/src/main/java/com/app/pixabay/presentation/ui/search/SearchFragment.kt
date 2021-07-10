package com.app.pixabay.presentation.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.pixabay.R
import com.app.pixabay.databinding.FragmentSearchBinding
import com.app.pixabay.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchImage()
        observeInFragment()
    }

    private fun observeInFragment() {
        viewModel.searchImageLiveData.observe(viewLifecycleOwner, {

            when (it) {
                is ResultWrapper.Success -> {
                    initRecyclerView(it.data)
                }
                is ResultWrapper.ErrorString -> {

                }
                is ResultWrapper.InProgress -> {

                }
            }

        })
    }

    private fun initRecyclerView(data: SearchResponse) {
        val adapter = SearchRVAdapter(data.hits)
        binding.searchRecyclerView.adapter = adapter
    }
}