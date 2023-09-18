package com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.ReviewTable

@Database(entities = [ItemTable::class, ReviewTable::class], version = 1, exportSchema = false)
abstract class ItemsRoomDatabase : RoomDatabase() {
    abstract fun itemProductDao(): ItemProductDao
}