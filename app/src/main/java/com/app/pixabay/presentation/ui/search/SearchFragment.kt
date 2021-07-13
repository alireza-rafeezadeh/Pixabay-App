package com.app.pixabay.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.SearchResponse
import com.app.core.util.getError
import com.app.core.util.gone
import com.app.core.util.textChanges
import com.app.core.util.visibile
import com.app.pixabay.R
import com.app.pixabay.databinding.FragmentSearchBinding
import com.app.pixabay.presentation.ui.search.paging.SearchComparator
import com.app.pixabay.presentation.ui.search.paging.SearchPagingAdapter
import com.app.pixabay.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var pagingAdapter: SearchPagingAdapter
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagingAdapter = SearchPagingAdapter(SearchComparator) { item ->
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                item.largeImageURL,
                item.user,
                item.tags
            ).also {
                findNavController().navigate(it)
            }
        }
        binding.searchRecyclerView.adapter = pagingAdapter
        observeInFragment()
        addTextChangeListener()
    }



    private fun addTextChangeListener() {
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Error -> {
                        loadStates.getError().also { error ->
                            Toast.makeText(requireContext(), error ?: "", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is LoadState.Loading -> {
                        binding.searchProgressBar.visibile()
                    }
                    is LoadState.NotLoading -> {
                        binding.searchProgressBar.gone()
                    }
                }
            }
        }

        binding.editTextTextPersonName
            .textChanges()
            .debounce(400)
            .filter { it?.isNotBlank() == true }
            .onEach {
//                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//                viewModel.searchImage(it.toString())
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.searchWithPaging(it.toString())
                        .catch {
                            Log.i("a_t_c_l_exc", "addTextChangeListener: ")
                        }
                        .collectLatest { pagingData ->
                            pagingAdapter.submitData(pagingData)
                        }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun observeInFragment() {
        viewModel.searchImageLiveData.observe(viewLifecycleOwner, {

            when (it) {
                is ResultWrapper.Success -> {
//                    initRecyclerView(it.data)
                }
                is ResultWrapper.ErrorString -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.InProgress -> {

                }
            }

        })


//        lifecycleScope.launch {
////            viewModel.currentSearchResult?.collectLatest { pagingData ->
////                pagingAdapter.submitData(pagingData)
////            }
//            viewModel.searchWithPaging("Beauty").collectLatest { pagingData ->
//                pagingAdapter.submitData(pagingData)
//            }
//        }

    }

    private fun initRecyclerView(data: SearchResponse) {
        val adapter = SearchRVAdapter(data.hits)
        binding.searchRecyclerView.adapter = adapter
    }
}