package ru.stas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.stas.model.FlashSale
import ru.stas.repository.ProductRepositoryImpl

const val TAG = "Заебало"

class FlashSaleViewModel(private val repository: ProductRepositoryImpl) : ViewModel() {

    private val _flashSaleLiveData = MutableLiveData<List<FlashSale>>()
    val flashSaleLiveData: LiveData<List<FlashSale>> = _flashSaleLiveData

    fun getFlashSales() {
        viewModelScope.launch {
            val result = repository.getFlashSales()
            _flashSaleLiveData.postValue(result.flash_sale)
        }
    }
}

