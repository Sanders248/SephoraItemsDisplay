package com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices

import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.BrandTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.ImageTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.ReviewTable

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