package com.example.retrofitapp.presentation.recyclerViewAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofitapp.data.retrofit.pojo.Product

class ProductDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}