package com.example.sephoraitemsdisplay.repositories

import com.example.sephoraitemsdisplay.domains.models.Review
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.BrandResponse
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.ImageResponse
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.ItemResponse
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.ItemReviewResponse
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.models.ReviewResponse
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.BrandTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ImageTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ItemWithReviews
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localService.models.ReviewTable
import kotlin.random.Random


fun getNextItemWithReviews() = ItemWithReviews(
    item = ItemTable(
        productId = Random.nextInt(),
        productName = Random.nextString(),
        description = Random.nextString(),
        price = Random.nextFloat(),
        isProductSet = Random.nextBoolean(),
        isSpecialBrand = Random.nextBoolean(),
        image = getNextImageTable(),
        brand = getNextBrandTable()
    ), reviews = List(Random.nextInt(1, 8)) { getNextReviewTable() }
)

fun getNextImageTable() = ImageTable(small = Random.nextString(), large = null)
fun getNextBrandTable() = BrandTable(id = Random.nextString(), name = Random.nextString())
fun getNextReviewTable() = ReviewTable(
    productId = Random.nextInt(),
    hide = Random.nextBoolean(),
    name = Random.nextString(),
    text = Random.nextString(),
    rating = Random.nextDouble(0.0, 10.0).toFloat()
)
fun getNextItemTable() = ItemTable(
    productId = Random.nextInt(),
    productName = Random.nextString(),
    description = Random.nextString(),
    price = Random.nextFloat(),
    isProductSet = Random.nextBoolean(),
    isSpecialBrand = Random.nextBoolean(),
    image = getNextImageTable(),
    brand = getNextBrandTable()
)

fun getNextItemResponse() = ItemResponse(
    productId = Random.nextInt(),
    productName = Random.nextString(),
    description = Random.nextString(),
    price = Random.nextFloat(),
    isProductSet = Random.nextBoolean(),
    isSpecialBrand = Random.nextBoolean(),
    image = getNextImageResponse(),
    brand = getNextBrandResponse()
)

fun getNextImageResponse() = ImageResponse(small = Random.nextString(), large = null)
fun getNextBrandResponse() = BrandResponse(id = Random.nextString(), name = Random.nextString())

fun getNextReviewResponse() = ReviewResponse(
    productId = Random.nextInt(),
    hide = Random.nextBoolean(),
    reviews = List(Random.nextInt(1, 8)) { getNextItemReviewResponse() },
)

fun getNextItemReviewResponse() = ItemReviewResponse(
    name = Random.nextString(),
    text = Random.nextString(),
    rating = Random.nextDouble(0.0, 10.0).toFloat()
)

fun getNextReview() = Review(
    name = Random.nextString(),
    text = Random.nextString(),
    rating = Random.nextDouble(0.0, 10.0).toFloat()
)

private fun Random.nextString(length: Int = 10) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
