package com.leviancode.android.infotextest.ui.screens.city_list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import com.leviancode.android.infotextest.data.repositories.CitiesRepository
import com.leviancode.android.infotextest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) : BaseViewModel() {
    var firstLoading = true
    var searchModeOn = false
    private val _cities = MutableSharedFlow<PagingData<CityUI>>()
    val cities: SharedFlow<PagingData<CityUI>> = _cities

    fun loadAllCities() {
        viewModelScope.launch {
            citiesRepository.fetchCities()
                .cachedIn(viewModelScope)
                .collect {
                    _cities.emit(it)
                }
        }
    }

    fun findCity(query: String?) {
        if (query.isNullOrBlank()) {
            loadAllCities()
            return
        }
        viewModelScope.launch {
            citiesRepository.findCity(query)
                .cachedIn(viewModelScope)
                .collect {
                    _cities.emit(it)
                }
        }
    }
}