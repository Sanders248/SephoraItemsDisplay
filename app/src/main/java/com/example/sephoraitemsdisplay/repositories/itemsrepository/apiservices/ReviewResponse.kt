package com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("product_id")
    val productId: Int,
    @SerialName("hide")
    val hide: Boolean?,
    @SerialName("reviews")
    val reviews: List<ItemReviewResponse>
)

@Serializable
data class ItemReviewResponse(
    @SerialName("name")
    val name: String?,
    @SerialName("text")
    val text: String?,
    @SerialName("rating")
    val rating: Float?
)
