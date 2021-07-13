package com.app.pixabay.presentation.ui.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.app.pixabay.R
import com.app.pixabay.presentation.di.AppFragmentFactory
import com.app.pixabay.presentation.ui.search.paging.SearchPagingAdapter
import com.app.pixabay.util.atPosition
import com.app.pixabay.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@FlowPreview
@LargeTest
class SearchFragmentIntegrationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        AppFragmentFactory().also {
            launchFragmentInHiltContainer<SearchFragment>(fragmentFactory = it) { }
        }
    }

    @Test
    fun should_display_search_edit_text() {
        onView(withId(R.id.editTextTextPersonName))
            .check(matches(isDisplayed()))
        onView(withId(R.id.editTextTextPersonName))
            .check(matches(withText("flowers")))
    }

    @Test
    fun should_display_recycler_view() {
        onView(withId(R.id.search_recycler_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun should_display_texts_in_recycler_view_after_default_search() {
        runBlocking {
            delay(5000)
        }
        onView(withId(R.id.search_recycler_view))
            .check(
                matches(
                    atPosition(0, hasDescendant(withId(R.id.user_text_view)))
                )
            )
            .check(
                matches(
                    atPosition(0, hasDescendant(withId(R.id.likes_text_view)))
                )
            )
            .check(
                matches(
                    atPosition(0, hasDescendant(withId(R.id.views_text_view)))
                )
            )
            .check(
                matches(
                    atPosition(0, hasDescendant(withId(R.id.downloads_text_view)))
                )
            )
    }

    @Test
    fun should_search_a_word_and_scroll_to_a_certain_position_in_recycler_view() {

        onView(withId(R.id.editTextTextPersonName))
            .perform(clearText())

        onView(withId(R.id.editTextTextPersonName))
            .perform(typeText("beautiful"), ViewActions.closeSoftKeyboard())

        runBlocking {
            delay(5000)
        }

        onView(withId(R.id.search_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<SearchPagingAdapter.HitViewHolder>(13))
    }
}


