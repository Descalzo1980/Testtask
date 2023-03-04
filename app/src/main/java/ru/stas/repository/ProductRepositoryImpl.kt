package ru.stas.repository

import ru.stas.api.RetrofitInstance
import ru.stas.model.Flash
import ru.stas.model.Latest

class ProductRepositoryImpl : ProductRepository {
    private val apiService = RetrofitInstance.apiService

    override suspend fun getFlashSales(): Flash {
        return apiService.getFlashSales().body()!!
    }

    override suspend fun getLatest(): Latest {
        return apiService.getLatestProducts().body()!!
    }
}