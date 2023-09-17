package com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("product_id")
    val productId: Int,
    @SerialName("hide")
    val hide: Boolean? = null,
    @SerialName("reviews")
    val reviews: List<ItemReviewResponse>? = null
)

@Serializable
data class ItemReviewResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("rating")
    val rating: Float? = null
)
