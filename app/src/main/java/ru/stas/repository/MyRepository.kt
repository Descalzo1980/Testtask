package ru.stas.repository

import ru.stas.api.RetrofitInstance
import ru.stas.model.Flash
import ru.stas.model.FlashItem
import ru.stas.model.Latest

class MyRepository {

    private val apiService = RetrofitInstance.apiService

    suspend fun getFlashSales(): Flash {
        return apiService.getFlashSales()
    }

    suspend fun getLatestProducts(): Latest {
        return apiService.getLatestProducts()
    }

    suspend fun getItemsFlash(): FlashItem{
        return apiService.getItemsFlash()
    }
}