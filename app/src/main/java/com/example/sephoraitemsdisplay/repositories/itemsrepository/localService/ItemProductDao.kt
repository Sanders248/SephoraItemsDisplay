package com.example.sephoraitemsdisplay.repositories.itemsrepository.localService

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemProductDao {
    @Transaction
    @Query("SELECT * FROM item_table")
    fun getUsersWithPlaylists(): Flow<List<ItemWithReviews>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertItems(items: List<ItemTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertReviews(items: List<ReviewTable>)
}