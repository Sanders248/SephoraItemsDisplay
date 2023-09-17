package com.example.sephoraitemsdisplay.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sephoraitemsdisplay.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.sephoraitemsdisplay.features.main.models.MainItem
import com.example.sephoraitemsdisplay.features.main.ui.MainItemListAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:  MainViewModel by viewModels()

    private lateinit var viewAdapter: MainItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView(findViewById(R.id.recycler_view))

        viewModel.items.observe(this, ::onItemsReceived)
    }

    private fun onItemsReceived(items: List<MainItem>) {
        viewAdapter.items = items
        viewAdapter.notifyDataSetChanged()
    }

    private fun initListView(recyclerView: RecyclerView) {
        viewAdapter = MainItemListAdapter(emptyList())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = viewAdapter
    }
}