package com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices

import retrofit2.Response
import retrofit2.http.GET


interface ItemsApiService {
    @GET("items.json")
    suspend fun getItems(): Response<List<ItemResponse>>

    @GET("reviews.json")
    suspend fun getReviews(): Response<List<ReviewResponse>>
}