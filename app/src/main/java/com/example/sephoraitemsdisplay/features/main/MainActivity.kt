package com.example.sephoraitemsdisplay.features.main

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.sephoraitemsdisplay.R
import com.example.sephoraitemsdisplay.features.main.models.MainEvent
import com.example.sephoraitemsdisplay.features.main.models.MainItem
import com.example.sephoraitemsdisplay.features.main.ui.MainItemListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:  MainViewModel by viewModels()

    private lateinit var viewAdapter: MainItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView(findViewById(R.id.recycler_view))

        findViewById<EditText>(R.id.search_edit_text).doAfterTextChanged { editable ->
            viewModel.onSearchTextChanged(editable?.toString() ?: "")
        }

        viewModel.items.observe(this, ::onItemsReceived)
        viewModel.event.observe(this, ::onEventReceived)
    }

    private fun onItemsReceived(items: List<MainItem>) {
        viewAdapter.updateItems(items)
    }

    private fun onEventReceived(event: MainEvent) {
        when(event) {
            MainEvent.Idle -> Unit
            MainEvent.DisplayError -> Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListView(recyclerView: RecyclerView) {
        viewAdapter = MainItemListAdapter(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = viewAdapter
    }
}