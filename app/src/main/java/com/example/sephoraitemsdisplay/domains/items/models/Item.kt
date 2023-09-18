package com.example.sephoraitemsdisplay.domains.items.models

data class Item(
    val productId: Int,
    val productName: String?,
    val description: String?,
    val price: Float?,
    val image: Image?,
    val brand: Brand?,
    val reviews: List<Review>
)

data class Image(
    val small: String?,
    val large: String?
)

data class Brand(
    val id: String?,
    val name: String?
)

data class Review(
    val name: String?,
    val text: String?,
    val rating: Float?,
)