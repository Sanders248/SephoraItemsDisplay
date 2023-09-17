package com.example.sephoraitemsdisplay.features.main.models

data class MainItem(
    val productId: Int,
    val productName: String?,
    val description: String?,
    val price: Float?,
    val image: MainImage?,
    val brand: MainBrand?,
    val averageReviewScore: Double?,
    val reviews: List<MainReview>
)

data class MainImage(
    val small: String?,
    val large: String?
)

data class MainBrand(
    val id: String?,
    val name: String?
)

data class MainReview(
    val name: String?,
    val text: String?,
    val rating: Float?,
)