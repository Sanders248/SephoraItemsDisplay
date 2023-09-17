package com.example.sephoraitemsdisplay.domains.usecases

import com.example.sephoraitemsdisplay.domains.models.Item
import com.example.sephoraitemsdisplay.domains.repositories.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsUseCase @Inject constructor(
    private val repository: ItemsRepository
) {
    val items: Flow<List<Item>> = repository.items

    suspend fun refresh() = repository.refresh()
}