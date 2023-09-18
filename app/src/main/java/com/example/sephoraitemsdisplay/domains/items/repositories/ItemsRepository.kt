package com.example.sephoraitemsdisplay.domains.items.repositories

import com.example.sephoraitemsdisplay.domains.items.models.Item
import com.example.sephoraitemsdisplay.domains.items.models.Review
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    val items: Flow<List<Item>>

    fun getReviews(productId: Int): Flow<List<Review>>

    suspend fun refresh(): Result<Unit>
}