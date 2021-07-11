package com.app.pixabay.presentation.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.pixabay.R
import com.app.pixabay.databinding.FragmentSearchBinding
import com.app.pixabay.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import com.app.core.util.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.searchImage()
        observeInFragment()
        addTextChangeListener()
    }

    private fun addTextChangeListener() {
        binding.editTextTextPersonName
            .textChanges()
            .debounce(400)
            .filter { it?.isNotBlank() == true }
            .onEach {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.searchImage(it.toString())
            }
            .launchIn(lifecycleScope)
    }

    private fun observeInFragment() {
        viewModel.searchImageLiveData.observe(viewLifecycleOwner, {

            when (it) {
                is ResultWrapper.Success -> {
                    initRecyclerView(it.data)
                }
                is ResultWrapper.ErrorString -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_SHORT).show()
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