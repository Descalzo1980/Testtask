package ru.stas.repository

import retrofit2.HttpException
import ru.stas.api.RetrofitInstance
import ru.stas.model.FlashSale
import ru.stas.model.LatestX

class MyRepository: ProductRepository {

    private val apiService = RetrofitInstance.apiService

    override suspend fun getFlashSales(): List<FlashSale> {
        val response = apiService.getFlashSales()
        if (response.isSuccessful) {
            return response.body()?.flash_sale ?: emptyList()
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getLatest(): List<LatestX> {
        val response = apiService.getLatestProducts()
        if (response.isSuccessful) {
            return response.body()?.latest ?: emptyList()
        } else {
            throw HttpException(response)
        }
    }
}
