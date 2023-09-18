package com.example.sephoraitemsdisplay.features.reviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sephoraitemsdisplay.R
import com.example.sephoraitemsdisplay.domains.items.models.Review
import com.example.sephoraitemsdisplay.features.reviews.ui.ReviewsItemListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsActivity : AppCompatActivity() {

    companion object {
        internal const val PRODUCT_ID_KEY = "product_id"

        fun newIntent(context: Context, productId: Int): Intent =
            Intent(context, ReviewsActivity::class.java).apply {
                putExtra(PRODUCT_ID_KEY, productId)
            }
    }

    private val viewModel: ReviewsViewModel by viewModels()

    private lateinit var viewAdapter: ReviewsItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        initListView(findViewById(R.id.recycler_view))

        viewModel.reviews.observe(this, ::onItemsReceived)

        findViewById<ImageButton>(R.id.switch_order).setOnClickListener {
            viewModel.onReverseOrderClicked()
        }
    }

    private fun onItemsReceived(items: List<Review>) {
        viewAdapter.updateItems(items)
    }

    private fun initListView(recyclerView: RecyclerView) {
        viewAdapter = ReviewsItemListAdapter(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = viewAdapter
    }
}