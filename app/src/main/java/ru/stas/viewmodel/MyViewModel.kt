package ru.stas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.stas.model.FlashSale
import ru.stas.model.LatestX
import ru.stas.repository.MyRepository

class MyViewModel() : ViewModel() {

    private val myRepository = MyRepository()

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
                val response = myRepository.getFlashSales()
                    flashSales.postValue(response.flash_sale)
                Log.d("TAG", "ответ $response")
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }
    }

    private suspend fun latestLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = myRepository.getLatestProducts()
                    latestProducts.postValue(response.latest)
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

