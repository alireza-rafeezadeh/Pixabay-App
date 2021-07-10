package com.app.pixabay.presentation.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.app.pixabay.presentation.ui.search.SearchFragment
import javax.inject.Inject

class AppFragmentFactory @Inject constructor() :
    FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            SearchFragment::class.java.name -> {
                SearchFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}