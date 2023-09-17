package com.example.sephoraitemsdisplay.domains.repositories

import com.example.sephoraitemsdisplay.domains.models.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    val items: Flow<List<Item>>

    suspend fun refresh(): Result<Unit>
}