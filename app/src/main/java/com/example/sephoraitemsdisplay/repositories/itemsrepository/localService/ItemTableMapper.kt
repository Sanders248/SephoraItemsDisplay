package com.example.sephoraitemsdisplay.repositories.itemsrepository.localService

import com.example.sephoraitemsdisplay.domains.models.Brand
import com.example.sephoraitemsdisplay.domains.models.Image
import com.example.sephoraitemsdisplay.domains.models.Item
import com.example.sephoraitemsdisplay.domains.models.Review


fun toItem(itemWithReviews: ItemWithReviews): Item = with(itemWithReviews) {
    Item(
        productId = item.productId,
        productName = item.productName,
        description = item.description,
        price = item.price,
        image = item.image?.toImage(),
        brand = item.brand?.toBrand(),
        reviews = reviews.map(::toReview)
    )
}

fun ImageTable.toImage() = Image(small = small, large = large)

fun BrandTable.toBrand() = Brand(id = id, name = name)

fun toReview(reviewTable: ReviewTable) = with(reviewTable) {
    Review(name = name, text = text, rating = rating)
}