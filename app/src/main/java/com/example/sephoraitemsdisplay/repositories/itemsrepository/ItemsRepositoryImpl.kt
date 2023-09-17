package com.example.sephoraitemsdisplay.repositories.itemsrepository

import com.example.sephoraitemsdisplay.domains.models.Item
import com.example.sephoraitemsdisplay.domains.repositories.ItemsRepository
import com.example.sephoraitemsdisplay.libraries.network.apiCall
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.ItemsApiService
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.toItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.toReviewTables
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.ItemProductDao
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.toItem
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val apiService: ItemsApiService,
    private val itemDao: ItemProductDao
): ItemsRepository {
    override val items: Flow<List<Item>> = itemDao.getUsersWithPlaylists().map {
        it.map(::toItem)
    }

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

        itemDao.insertItems(itemsTable.getOrNull() ?: emptyList())
        itemDao.insertReviews(reviewsTable.getOrNull() ?: emptyList())

        return Result.success(Unit)
    }
}