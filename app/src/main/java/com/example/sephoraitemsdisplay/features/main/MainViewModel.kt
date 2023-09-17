package com.example.sephoraitemsdisplay.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sephoraitemsdisplay.domains.models.Item
import com.example.sephoraitemsdisplay.domains.usecases.ItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {
    // Todo use another Items
    val items: LiveData<List<Item>> = itemsUseCase.items.asLiveData()

    init {
        viewModelScope.launch {
            itemsUseCase.refresh()
        }
    }
}