package ru.stas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.stas.api.RetrofitInstance
import ru.stas.model.FlashSale
import ru.stas.model.LatestX

class MyViewModel : ViewModel() {

    private val apiService = RetrofitInstance.apiService

    var flashSales = MutableLiveData<List<FlashSale>>()
    var latestProducts = MutableLiveData<List<LatestX>>()

    init {
        viewModelScope.launch {
            flashSalesLiveData()
            latestLiveData()
        }
    }

    private suspend fun flashSalesLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getFlashSales()
                if (response.isSuccessful && response.body() != null) {
                    flashSales.postValue(response.body()!!.flash_sale)
                } else {
                    response.errorBody()
                }
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }
    }

    private suspend fun latestLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getLatestProducts()
                if (response.isSuccessful && response.body() != null) {
                    latestProducts.postValue(response.body()!!.latest)
                } else {
                    response.errorBody()
                }
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }
    }

    fun observeLatestProducts(): LiveData<List<LatestX>> {
        return latestProducts
    }

    fun observeFlashSale(): LiveData<List<FlashSale>> {
        return flashSales
    }
}

