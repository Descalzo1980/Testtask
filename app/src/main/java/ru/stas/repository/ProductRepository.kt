package ru.stas.repository

import ru.stas.model.Flash
import ru.stas.model.Latest

interface ProductRepository {
    suspend fun getFlashSales(): Flash
    suspend fun getLatest(): Latest
}