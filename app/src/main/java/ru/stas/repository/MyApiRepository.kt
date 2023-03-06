package ru.stas.repository

import retrofit2.Call
import ru.stas.api.RetrofitInstance
import ru.stas.model.Flash
import ru.stas.model.Latest

class MyApiRepository {

    private val apiService = RetrofitInstance.apiService

    fun fetchFlashSales(): Call<Flash> = apiService.getFlashSales()

    fun fetchLatestProducts(): Call<Latest> = apiService.getLatestProducts()
}
