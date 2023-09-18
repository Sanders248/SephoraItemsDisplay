package com.example.sephoraitemsdisplay.repositories.itemsrepository

import com.example.sephoraitemsdisplay.domains.items.models.Review
import com.example.sephoraitemsdisplay.getNextItemResponse
import com.example.sephoraitemsdisplay.getNextItemWithReviews
import com.example.sephoraitemsdisplay.getNextReview
import com.example.sephoraitemsdisplay.getNextReviewResponse
import com.example.sephoraitemsdisplay.repositories.itemsrepository.apiservices.ItemsApiService
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.ItemProductDao
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.ItemTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.ItemWithReviews
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.ReviewTable
import com.example.sephoraitemsdisplay.repositories.itemsrepository.localservices.models.toItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemsRepositoryTest {
    private fun getItemsRepository(
        apiService: ItemsApiService = mockk(),
        itemDao: ItemProductDao = FakeItemProductDao()
    ) = ItemsRepositoryImpl(apiService, itemDao)

    @Test
    fun `test items return mapped flow`() = runTest {
        // Given
        val itemDao = FakeItemProductDao()
        val itemsRepository = getItemsRepository(itemDao = itemDao)
        val data = itemDao.itemsWithReviews

        // When
        itemsRepository.items.collect {
            // Then
            val expected = data.map(::toItem)
            assertEquals(expected, it)
        }
    }

    @Test
    fun `test getReviews return reviews`() = runTest {
        // Given
        val itemDao = FakeItemProductDao()
        val itemsRepository = getItemsRepository(itemDao = itemDao)
        val data = itemDao.reviews

        // When
        itemsRepository.getReviews(Random.nextInt()).collect {
            // Then
            assertEquals(data, it)
        }
    }

    // region refresh

    @Test
    fun `test refresh when getItems return an error`() = runTest {
        // Given
        val apiService: ItemsApiService = mockk {
            coEvery { getItems() } returns Response.error(404, "".toResponseBody())
        }
        val itemsRepository = getItemsRepository(apiService = apiService)

        // When
        val result = itemsRepository.refresh()

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `test refresh when getReviews return an error`() = runTest {
        // Given
        val apiService: ItemsApiService = mockk {
            coEvery { getItems() } returns Response.success(listOf(getNextItemResponse()))
            coEvery { getReviews() } returns Response.error(404, "".toResponseBody())
        }
        val itemsRepository = getItemsRepository(apiService = apiService)

        // When
        val result = itemsRepository.refresh()

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `test refresh on success`() = runTest {
        // Given
        val apiService: ItemsApiService = mockk {
            coEvery { getItems() } returns Response.success(listOf(getNextItemResponse()))
            coEvery { getReviews() } returns Response.success(listOf(getNextReviewResponse()))
        }
        val itemDao = spyk(FakeItemProductDao())
        val itemsRepository = getItemsRepository(apiService = apiService, itemDao = itemDao)

        // When
        val result = itemsRepository.refresh()

        // Then
        coVerify { itemDao.insertItems(any()) }
        coVerify { itemDao.insertReviews(any()) }
        assertTrue(result.isSuccess)
    }

    // endregion

    inner class FakeItemProductDao: ItemProductDao {
        val itemsWithReviews= List(Random.nextInt(1, 8)) { getNextItemWithReviews() }
        val reviews = List(Random.nextInt(1, 10)) { getNextReview() }

        override fun getItemsWithReviews(): Flow<List<ItemWithReviews>> = flow { emit(itemsWithReviews) }
        override fun getReviewsFromItem(productId: Int): Flow<List<Review>> = flow { emit(reviews) }
        override suspend fun insertItems(items: List<ItemTable>) = Unit
        override suspend fun insertReviews(items: List<ReviewTable>) = Unit
    }
}