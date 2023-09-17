package com.example.sephoraitemsdisplay.features.main.models

import com.example.sephoraitemsdisplay.domains.models.Brand
import com.example.sephoraitemsdisplay.domains.models.Image
import com.example.sephoraitemsdisplay.domains.models.Item
import com.example.sephoraitemsdisplay.domains.models.Review


fun Item.toMainItem(averageReviewScore: Double?) = MainItem(
    productId = productId,
    productName = productName,
    description = description,
    price = price,
    image = image?.toMainImage(),
    brand = brand?.toMainBrand(),
    averageReviewScore = averageReviewScore,
    reviews = reviews.map(::toMainReview)
)

fun Image.toMainImage() = MainImage(small = small, large = large)

fun Brand.toMainBrand() = MainBrand(id = id, name = name)

fun toMainReview(review: Review) = with(review) {
    MainReview(name = name, text = text, rating = rating)
}