package com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models

import androidx.room.Embedded
import androidx.room.Relation

data class ItemWithReviews(
    @Embedded val item: ItemTable,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_id"
    )
    val reviews: List<ReviewTable>
)
