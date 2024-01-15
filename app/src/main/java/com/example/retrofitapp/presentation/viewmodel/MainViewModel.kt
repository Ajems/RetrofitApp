package com.example.retrofitapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitapp.data.retrofit.apiservice.ProductApi
import com.example.retrofitapp.domain.useCases.GetProductInfoUseCase
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.data.retrofit.pojo.Product
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application): AndroidViewModel(application) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val productApi = retrofit.create(ProductApi::class.java)

    private val getProductInfoUseCase = GetProductInfoUseCase(productApi)

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private var loadedProducts = 0
    private var step = 10

    fun loadProduct() {
        viewModelScope.launch {
            for (id in loadedProducts + 1..loadedProducts + step) {
                val product = getProductInfoUseCase(id)
                Log.d(PRODUCT_LOAD_TAG, product.toString())

                val currentList = _productList.value.orEmpty().toMutableList()
                currentList.add(product)

                _productList.postValue(currentList)
            }
            loadedProducts += step
        }
    }

    companion object {
        const val PRODUCT_LOAD_TAG = "PRODUCT_LOAD_TAG"
    }
}