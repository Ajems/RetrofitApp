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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val interceptor = createInterceptor(HttpLoggingInterceptor.Level.BODY)
    private val client = createClient(interceptor)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val productApi = retrofit.create(ProductApi::class.java)

    private val getProductInfoUseCase = GetProductInfoUseCase(productApi)

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private var loadedProducts = 0
    private var step = 3

    fun loadProduct() {
        viewModelScope.launch {
            for (id in loadedProducts + 1..loadedProducts + step) {
                loadedProducts++
                val product = getProductInfoUseCase(id)
                Log.d(PRODUCT_LOAD_TAG, product.toString())

                val currentList = _productList.value.orEmpty().toMutableList()
                currentList.add(product)

                _productList.postValue(currentList)
            }
        }
    }

    private fun createInterceptor(level: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = level
        return interceptor
    }

    private fun createClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    companion object {
        const val PRODUCT_LOAD_TAG = "PRODUCT_LOAD_TAG"
    }
}