package com.example.sephoraitemsdisplay.features.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sephoraitemsdisplay.domains.items.models.Review
import com.example.sephoraitemsdisplay.domains.items.usecases.ItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    itemsUseCase: ItemsUseCase,
    state: SavedStateHandle
) : ViewModel() {

    private val productId = state.get<Int>(ReviewsActivity.PRODUCT_ID_KEY) ?: -1

    private val isDescOrder = MutableStateFlow(true)

    val reviews: LiveData<List<Review>> =
        itemsUseCase.getReviewsFromItem(productId).combine(isDescOrder) { reviews, isDescOrder ->
            if (isDescOrder) reviews else reviews.asReversed()
        }.asLiveData()

    fun onReverseOrderClicked() {
        viewModelScope.launch {
            isDescOrder.emit(!isDescOrder.value)
        }
    }
}