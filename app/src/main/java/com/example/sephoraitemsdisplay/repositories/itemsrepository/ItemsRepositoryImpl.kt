package com.example.sephoraitemsdisplay.repositories.itemsrepository

import com.example.sephoraitemsdisplay.domains.items.models.Item
import com.example.sephoraitemsdisplay.domains.items.models.Review
import com.example.sephoraitemsdisplay.domains.items.repositories.ItemsRepository
import com.example.sephoraitemsdisplay.libraries.network.apiCall
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.ItemsApiService
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.toItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.toReviewTables
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.ItemProductDao
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.toItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val apiService: ItemsApiService,
    private val itemDao: ItemProductDao
): ItemsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val items: Flow<List<Item>> = itemDao.getItemsWithReviews().mapLatest {
        it.map(::toItem)
    }

    override fun getReviews(productId: Int): Flow<List<Review>> =
        itemDao.getReviewsFromItem(productId)

    override suspend fun refresh(): Result<Unit> {
        val itemsTable = apiCall {
            apiService.getItems()
        }.onFailure {
            return Result.failure(it)
        }.map {
            it.map(::toItemTable)
        }

        val reviewsTable = apiCall {
            apiService.getReviews()
        }.onFailure {
            return Result.failure(it)
        }.map {
            it.flatMap(::toReviewTables)
        }

        itemDao.insertItems(itemsTable.getOrThrow())
        itemDao.insertReviews(reviewsTable.getOrThrow())

        return Result.success(Unit)
    }
}