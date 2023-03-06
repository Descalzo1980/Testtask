package ru.stas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.stas.model.FlashSale
import ru.stas.model.LatestX
import ru.stas.repository.MyRepository


class MyViewModel() : ViewModel() {

    private val repository = MyRepository()

    private val _flashSalesLiveData = MutableLiveData<List<FlashSale>>()
    val flashSalesLiveData: LiveData<List<FlashSale>>
        get() = _flashSalesLiveData

    private val _latestProductsLiveData = MutableLiveData<List<LatestX>>()
    val latestProductsLiveData: LiveData<List<LatestX>>
        get() = _latestProductsLiveData

    init {
        fetchFlashSales()
        fetchLatestProducts()
    }

    private fun fetchFlashSales() {
        viewModelScope.launch {
            try {
                _flashSalesLiveData.value = repository.getFlashSales()
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    private fun fetchLatestProducts() {
        viewModelScope.launch {
            try {
                _latestProductsLiveData.value = repository.getLatest()
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}




