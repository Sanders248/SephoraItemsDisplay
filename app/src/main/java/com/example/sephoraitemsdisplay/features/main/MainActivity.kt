package com.example.sephoraitemsdisplay.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sephoraitemsdisplay.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:  MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.items.observe(this) {
            findViewById<TextView>(R.id.text).text = it.firstOrNull()?.productName ?: "Unknown"
        }
    }
}