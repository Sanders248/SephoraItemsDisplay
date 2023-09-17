package com.example.sephoraitemsdisplay.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sephoraitemsdisplay.domains.usecases.ItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.sephoraitemsdisplay.features.main.models.MainItem
import com.example.sephoraitemsdisplay.features.main.models.toMainItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {

    private val searchTextFlow = MutableStateFlow("")

    val items: LiveData<List<MainItem>> = itemsUseCase.items.map { items ->
        items.map { item ->
            val averageReviewScore = itemsUseCase.calculateAverageReviewScore(item)
            item.toMainItem(averageReviewScore)
        }
    }.combine(searchTextFlow) { items, searchText ->
        items.filter { it.productName?.toLowerCase()?.contains(searchText.toLowerCase()) == true }
    }.asLiveData()

    init {
        viewModelScope.launch {
            //itemsUseCase.refresh()
            // todo display toast if failed
        }
    }

    fun onSearchTextChanged(searchText: String) {
        viewModelScope.launch {
            searchTextFlow.emit(searchText)
        }
    }
}