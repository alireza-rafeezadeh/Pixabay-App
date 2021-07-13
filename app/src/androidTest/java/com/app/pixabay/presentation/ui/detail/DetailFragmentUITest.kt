package com.app.pixabay.presentation.ui.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.app.pixabay.R
import com.app.pixabay.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@FlowPreview
@LargeTest
class DetailFragmentUITest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<DetailFragment>(fragmentArgs = getArgumentsBundle()) { }
    }

    @Test
    fun shouldDisplayLargeImage() {
        onView(withId(R.id.user_image_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayUserNameTextView() {
        onView(withId(R.id.user_text_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayTagsTextView() {
        onView(withId(R.id.user_image_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

}