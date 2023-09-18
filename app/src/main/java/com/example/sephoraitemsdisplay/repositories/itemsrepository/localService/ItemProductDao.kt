package com.example.sephoraitemsdisplay.repositories.itemsrepository.localService

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.sephoraitemsdisplay.domains.items.models.Review
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ItemWithReviews
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ReviewTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemProductDao {
    @Transaction
    @Query("SELECT * FROM item_table")
    fun getItemsWithReviews(): Flow<List<ItemWithReviews>>

    @Query("SELECT * FROM review_table WHERE product_id = :productId ORDER BY rating DESC")
    fun getReviewsFromItem(productId: Int): Flow<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertItems(items: List<ItemTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertReviews(items: List<ReviewTable>)
}