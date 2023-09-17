package com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models

import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.BrandTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ImageTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ReviewTable

fun toItemTable(itemResponse: ItemResponse) = with(itemResponse) {
    ItemTable(
        productId = productId,
        productName = productName,
        description = description,
        price = price,
        isProductSet = isProductSet,
        isSpecialBrand = isSpecialBrand,
        image = image?.toImageTable(),
        brand = brand?.toBrandTable()
    )
}

fun ImageResponse.toImageTable() = ImageTable(small = small, large = large)

fun BrandResponse.toBrandTable() = BrandTable(id = id, name = name)

fun toReviewTables(reviewResponse: ReviewResponse): List<ReviewTable> = with(reviewResponse) {
    reviews?.map {
        ReviewTable(
            productId = productId,
            hide = hide,
            name = it.name,
            text = it.text,
            rating = it.rating
        )
    } ?: emptyList()
}