package com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class ItemTable(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: Int,
    @ColumnInfo(name = "product_name")
    val productName: String? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "price")
    val price: Float? = null,
    @ColumnInfo(name = "is_productSet")
    val isProductSet: Boolean? = null,
    @ColumnInfo(name = "is_special_brand")
    val isSpecialBrand: Boolean? = null,
    @Embedded
    val image: ImageTable? = null,
    @Embedded
    val brand: BrandTable? = null,
)

@Entity
data class ImageTable(
    @ColumnInfo(name = "small")
    val small: String?,
    @ColumnInfo(name = "large")
    val large: String?
)

@Entity
data class BrandTable(
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "name")
    val name: String?
)