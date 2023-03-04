package ru.stas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.stas.model.LatestX
import ru.stas.repository.ProductRepositoryImpl

class LatestViewModel(private var repository: ProductRepositoryImpl) : ViewModel() {

    private var _latestLiveData = MutableLiveData<List<LatestX>>()
    var latestLiveData: LiveData<List<LatestX>> = _latestLiveData

    fun getLatest() {
        viewModelScope.launch {
            var result = repository.getLatest()
            _latestLiveData.postValue(result.latest)
        }
    }
}