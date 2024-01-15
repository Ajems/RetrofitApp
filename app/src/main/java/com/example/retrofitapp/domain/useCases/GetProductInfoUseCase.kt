package com.example.retrofitapp.domain.useCases

import com.example.retrofitapp.data.retrofit.apiservice.ProductApi
import com.example.retrofitapp.data.retrofit.pojo.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductInfoUseCase(private val productApi: ProductApi) {
    suspend operator fun invoke(id: Int): Product {
        return productApi.getProductById(id)
    }
}