package com.app.pixabay.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.pixabay.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
