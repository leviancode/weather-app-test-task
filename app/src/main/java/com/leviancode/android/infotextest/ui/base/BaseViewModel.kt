package com.leviancode.android.infotextest.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val minLoadingTime = 1000
    private var loadingStartTime = 0L

    fun showLoading(show: Boolean) {
        if (show) {
            loadingStartTime = System.currentTimeMillis()
            _loading.postValue(show)
        } else {
            val loadingTime = System.currentTimeMillis() - loadingStartTime
            if (loadingTime < minLoadingTime) {
                viewModelScope.launch {
                    delay(minLoadingTime - loadingTime)
                    _loading.postValue(show)
                }
            } else {
                _loading.postValue(show)
            }
        }
    }
}