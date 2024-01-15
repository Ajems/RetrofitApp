package com.example.retrofitapp.presentation.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.retrofitapp.R
import com.example.retrofitapp.data.retrofit.pojo.Product
import com.example.retrofitapp.databinding.ProductItemBinding

class ProductListAdapter : ListAdapter<Product, ProductItemViewHolder>(ProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val layout = R.layout.product_item
        val binding = DataBindingUtil.inflate<ProductItemBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = getItem(position)
        val binding = holder.binding
        binding.product = product
    }
}