package com.example.sephoraitemsdisplay.repositories.itemsrepository.localService

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ReviewTable

@Database(entities = [ItemTable::class, ReviewTable::class], version = 1, exportSchema = false)
abstract class ItemsRoomDatabase : RoomDatabase() {
    abstract fun itemProductDao(): ItemProductDao
}