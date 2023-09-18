package com.example.sephoraitemsdisplay.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sephoraitemsdisplay.domains.items.usecases.ItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.sephoraitemsdisplay.features.main.models.MainItem
import com.example.sephoraitemsdisplay.features.main.models.toMainItem
import com.example.sephoraitemsdisplay.features.main.models.MainEvent
import com.example.sephoraitemsdisplay.libraries.utils.SingleLiveEvent
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

    private val _event: MutableLiveData<MainEvent> = SingleLiveEvent()
    val event: LiveData<MainEvent> get() = _event

    init {
        viewModelScope.launch {
            itemsUseCase.refresh()
                .onFailure {
                    _event.value = MainEvent.DisplayError
                }
        }
    }

    fun onSearchTextChanged(searchText: String) {
        viewModelScope.launch {
            searchTextFlow.emit(searchText)
        }
    }
}