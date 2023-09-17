package com.example.sephoraitemsdisplay.features.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sephoraitemsdisplay.R
import com.example.sephoraitemsdisplay.features.main.models.MainItem

class MainItemListAdapter(var items: List<MainItem>) : RecyclerView.Adapter<MainItemListAdapter.ItemViewHolder>() {

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
            view.findViewById<TextView>(R.id.name).text = item.productName
        }
    }
}