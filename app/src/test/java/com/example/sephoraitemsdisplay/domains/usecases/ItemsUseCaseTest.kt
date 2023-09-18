package com.example.sephoraitemsdisplay.domains.usecases

import com.example.sephoraitemsdisplay.domains.models.Item
import com.example.sephoraitemsdisplay.domains.models.Review
import com.example.sephoraitemsdisplay.domains.repositories.ItemsRepository
import com.example.sephoraitemsdisplay.getNextItem
import com.example.sephoraitemsdisplay.getNextReview
import io.mockk.coVerify
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class ItemsUseCaseTest {

    private val itemsRepository = spyk(FakeItemsRepository())
    private val itemsUseCase = ItemsUseCase(itemsRepository)

    @Test
    fun `test items returns the list of items`() = runTest {
        // When
        itemsUseCase.items.collect {
            // Then
            val expected = itemsRepository.itemsData
            assertEquals(expected, it)
        }
    }

    @Test
    fun `test getReviewsFromItem returns the expected reviews`() = runTest {
        // When
        itemsUseCase.getReviewsFromItem(Random.nextInt()).collect {
            // Then
            val expected = itemsRepository.reviewsData
            assertEquals(expected, it)
        }
    }

    @Test
    fun `test refresh return result from repository`() = runTest {
        // When
        val result = itemsUseCase.refresh()

        // Then
        coVerify { itemsRepository.refresh() }
        assertEquals(Result.success(Unit), result)
    }

    // region calculateAverageReviewScore
    @Test
    fun `test calculateAverageReviewScore with only valid rating value`() {
        // Given
        val reviews = listOf(
            getNextReview(rating = 1f),
            getNextReview(rating = 9f),
            getNextReview(rating = 5f),
        )
        val item = getNextItem(reviews = reviews)

        // When
        val result = itemsUseCase.calculateAverageReviewScore(item)

        // Then
        val expected = (15f / 3).toDouble()
        assertEquals(expected, result)
    }

    @Test
    fun `test calculateAverageReviewScore with valid and null rating value`() {
        // Given
        val reviews = listOf(
            getNextReview(rating = 1f),
            getNextReview(rating = null),
            getNextReview(rating = 9f),
            getNextReview(rating = 5f),
            getNextReview(rating = null),
        )
        val item = getNextItem(reviews = reviews)

        // When
        val result = itemsUseCase.calculateAverageReviewScore(item)

        // Then
        val expected = (15f / 3).toDouble()
        assertEquals(expected, result)
    }

    @Test
    fun `test calculateAverageReviewScore with only null rating value`() {
        // Given
        val reviews = listOf(
            getNextReview(rating = null),
            getNextReview(rating = null),
        )
        val item = getNextItem(reviews = reviews)

        // When
        val result = itemsUseCase.calculateAverageReviewScore(item)

        // Then
        assertEquals(null, result)
    }

    // endregion

    inner class FakeItemsRepository : ItemsRepository {
        val itemsData = listOf(getNextItem())
        val reviewsData = listOf(getNextReview())

        override val items: Flow<List<Item>> = flow { emit(itemsData) }

        override fun getReviews(productId: Int): Flow<List<Review>> = flow { emit(reviewsData) }

        override suspend fun refresh(): Result<Unit> = Result.success(Unit)
    }
}