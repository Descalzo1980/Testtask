package ru.stas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.stas.model.FlashItem
import ru.stas.model.FlashSale
import ru.stas.model.LatestX
import ru.stas.repository.MyRepository

class MyViewModel() : ViewModel() {

    private val myRepository = MyRepository()

    private var _flashSale = MutableLiveData<List<FlashSale>>()
    private var _latestProducts = MutableLiveData<List<LatestX>>()
    private var _flashSaleItem = MutableLiveData<List<FlashItem>>()

    private val flashSale: LiveData<List<FlashSale>> get() = _flashSale
    private val latestProducts: LiveData<List<LatestX>> get() = _latestProducts
    private val flashSaleItem: LiveData<List<FlashItem>> get() = _flashSaleItem


    init {
        viewModelScope.launch {
            flashSalesLiveData()
            latestLiveData()
            flashSaleItemLiveData()
        }
    }

    private suspend fun flashSaleItemLiveData() {
        withContext(Dispatchers.IO){
            try {
                val response = myRepository.getItemsFlash()
                _flashSaleItem.postValue(listOf(response))
                Log.d("TAG", "ответ по item $response")
            }catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }
    }

    private suspend fun flashSalesLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = myRepository.getFlashSales()
                _flashSale.postValue(response.flash_sale)
//                Log.d("TAG", "ответ $response")
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }
    }

    private suspend fun latestLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = myRepository.getLatestProducts()
                _latestProducts.postValue(response.latest)
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }
    }

    fun observeLatestProducts(): LiveData<List<LatestX>> {
        return latestProducts
    }

    fun observeFlashSale(): LiveData<List<FlashSale>> {
        return flashSale
    }

    fun observeFlashSaleItem(): LiveData<List<FlashItem>>{
        return flashSaleItem
    }
}

