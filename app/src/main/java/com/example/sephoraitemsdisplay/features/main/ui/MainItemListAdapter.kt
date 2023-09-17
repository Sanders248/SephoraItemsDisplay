package com.example.sephoraitemsdisplay.features.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sephoraitemsdisplay.R
import com.example.sephoraitemsdisplay.features.main.models.MainItem
import com.example.sephoraitemsdisplay.features.reviews.ReviewsActivity

class MainItemListAdapter(
    private val context: Context,
    private var items: List<MainItem> = emptyList()
) : RecyclerView.Adapter<MainItemListAdapter.ItemViewHolder>() {

    fun updateItems(items: List<MainItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return ItemViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: MainItem) {
            view.findViewById<ImageView>(R.id.image).load(item.image?.small) {
                crossfade(true)
            }

            view.findViewById<TextView>(R.id.name).text = item.productName
            view.findViewById<TextView>(R.id.description).text = item.description
            view.findViewById<TextView>(R.id.rating).text = getRating(item.averageReviewScore)
            view.findViewById<TextView>(R.id.price).text = getPrice(item.price)

            view.findViewById<ConstraintLayout>(R.id.container).setOnClickListener {
                val newIntent = ReviewsActivity.newIntent(context, item.productId)
                context.startActivity(newIntent)
            }
        }

        private fun getRating(rating: Double?): String = rating?.let {
            context.getString(R.string.rating_display, it)
        } ?: ""

        private fun getPrice(price: Float?): String = price?.let {
            context.getString(R.string.price_display, it)
        } ?: ""

    }
}