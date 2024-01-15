package com.example.retrofitapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapp.databinding.ActivityMainBinding
import com.example.retrofitapp.presentation.recyclerViewAdapter.ProductListAdapter
import com.example.retrofitapp.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        addClickListener()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.productList.observe(this) {
            productListAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.productList) {
            val layoutManager = LinearLayoutManager(context)
            setLayoutManager(layoutManager)
            productListAdapter = ProductListAdapter()
            adapter = productListAdapter
        }
    }

    private fun addClickListener() {
        binding.buttonLoad.setOnClickListener {
            viewModel.loadProduct()
        }
    }
}