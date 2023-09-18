package com.example.sephoraitemsdisplay.domains.items.usecases

import com.example.sephoraitemsdisplay.domains.items.models.Item
import com.example.sephoraitemsdisplay.domains.items.models.Review
import com.example.sephoraitemsdisplay.domains.items.repositories.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsUseCase @Inject constructor(
    private val repository: ItemsRepository
) {
    val items: Flow<List<Item>> = repository.items

    fun getReviewsFromItem(productId: Int): Flow<List<Review>> = repository.getReviews(productId)

    suspend fun refresh() = repository.refresh()

    fun calculateAverageReviewScore(item: Item): Double? =
        item.reviews.mapNotNull { it.rating }.average().takeIf { !it.isNaN() }
}