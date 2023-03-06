package ru.stas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.stas.api.RetrofitInstance
import ru.stas.model.Flash
import ru.stas.model.FlashSale
import ru.stas.model.Latest
import ru.stas.model.LatestX


class MyViewModel : ViewModel() {

    private val apiService = RetrofitInstance.apiService

//    var flashSales = MutableLiveData<List<FlashSale>>()
//    var latestProducts = MutableLiveData<List<LatestX>>()


    var flashSales = MutableLiveData<List<FlashSale>>().apply {
        value = emptyList() // инициализируйте значение по умолчанию
    }
    var latestProducts = MutableLiveData<List<LatestX>>().apply {
        value = emptyList()
    }

    init {
        flashSalesLiveData()
        latestProducts()
    }

    fun flashSalesLiveData() {

        apiService.getFlashSales().enqueue(object : Callback<Flash> {
            override fun onResponse(call: Call<Flash>, response: Response<Flash>) {
                if (response.body() != null) {
                    flashSales.value = response.body()!!.flash_sale
                }
            }

            override fun onFailure(call: Call<Flash>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    fun latestProducts() {
        apiService.getLatestProducts().enqueue(object : Callback<Latest> {
            override fun onResponse(call: Call<Latest>, response: Response<Latest>) {
                if (response.body() != null) {
                    latestProducts.value = response.body()!!.latest
                }
            }

            override fun onFailure(call: Call<Latest>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun observeLatestProducts(): LiveData<List<LatestX>> {
        return latestProducts
    }

    fun observeFlashSale(): LiveData<List<FlashSale>> {
        return flashSales
    }
}







