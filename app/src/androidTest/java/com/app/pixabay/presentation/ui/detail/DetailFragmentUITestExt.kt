package com.app.pixabay.presentation.ui.detail

import android.os.Bundle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


private const val testLargeImageURL =
    "https://pixabay.com/get/gd64ea1ca651182a68f7d67bb26c924dc20d7b1ea92239484cd77017c91b9a22a471d472baa4f16539b3d90788842be60_1280.jpg"

@FlowPreview
@ExperimentalCoroutinesApi
fun DetailFragmentUITest.getArgumentsBundle() =
    Bundle().apply {
        putString(
            "large_image_url",
            testLargeImageURL
        )
        putString("user_name", "Test User")
        putString("tags", "rain, boots, umbrella")
    }
