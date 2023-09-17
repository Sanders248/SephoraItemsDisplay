package com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    @SerialName("product_id")
    val productId: Int,
    @SerialName("product_name")
    val productName: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("price")
    val price: Int?,
    @SerialName("images_url")
    val image: ImageResponse?,
    @SerialName("c_brand")
    val brand: BrandResponse?,
    @SerialName("is_productSet")
    val isProductSet: Boolean?,
    @SerialName("is_special_brand")
    val isSpecialBrand: Boolean?
)

@Serializable
data class ImageResponse(
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?
)

@Serializable
data class BrandResponse(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)