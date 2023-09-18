package com.example.sephoraitemsdisplay.features.reviews.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sephoraitemsdisplay.R
import com.example.sephoraitemsdisplay.domains.items.models.Review

class ReviewsItemListAdapter(
    private val context: Context,
    private var items: List<Review> = emptyList()
) : RecyclerView.Adapter<ReviewsItemListAdapter.ItemViewHolder>() {

    fun updateItems(items: List<Review>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.reviews_item, parent, false)
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
        fun bindItem(item: Review) {
            view.findViewById<TextView>(R.id.name).text = item.name ?: context.getString(R.string.missing_data)
            view.findViewById<TextView>(R.id.rating).text = getRating(item.rating)
            view.findViewById<TextView>(R.id.text).text = item.text ?: ""
        }

        private fun getRating(rating: Float?): String = rating?.let {
            context.getString(R.string.rating_display_small, it)
        } ?: ""
    }
}