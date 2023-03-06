package ru.stas.api


import retrofit2.Call
import retrofit2.http.GET
import ru.stas.model.Flash
import ru.stas.model.Latest

interface MyApiService {

    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    fun getFlashSales(): Call<Flash>

    @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    fun getLatestProducts(): Call<Latest>
}