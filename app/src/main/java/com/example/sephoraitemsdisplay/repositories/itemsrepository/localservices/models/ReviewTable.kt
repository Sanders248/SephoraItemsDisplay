package com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review_table")
data class ReviewTable (
    @ColumnInfo(name = "product_id")
    val productId: Int,
    @ColumnInfo(name = "hide")
    val hide: Boolean?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "text")
    val text: String?,
    @ColumnInfo(name = "rating")
    val rating: Float?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "review_id")
    var reviewId: Int = 0
}
