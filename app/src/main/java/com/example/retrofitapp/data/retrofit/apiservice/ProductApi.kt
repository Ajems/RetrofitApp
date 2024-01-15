package com.example.retrofitapp.data.retrofit.apiservice

import com.example.retrofitapp.data.retrofit.pojo.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}