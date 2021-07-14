package com.app.pixabay.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.pixabay.R
import com.app.pixabay.databinding.FragmentDetailBinding
import com.app.pixabay.presentation.util.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        Glide.with(requireContext()).load(args.largeImageUrl).into(binding.userImageView)
        binding.userTextView.text = args.userName
        binding.tagsTextView.text = args.tags
    }
}
