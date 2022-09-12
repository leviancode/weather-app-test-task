package com.leviancode.android.infotextest.ui.screens.city_weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leviancode.android.infotextest.data.entities.weather.Units
import com.leviancode.android.infotextest.data.repositories.WeatherRepository
import com.leviancode.android.infotextest.ui.base.BaseViewModel
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import com.leviancode.android.infotextest.ui.entities.location.toDataModel
import com.leviancode.android.infotextest.ui.entities.weather.WeatherUI
import com.leviancode.android.infotextest.ui.entities.weather.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {
    private val _currentWeather = MutableLiveData<WeatherUI>()
    val currentWeather: LiveData<WeatherUI> = _currentWeather

    fun loadCurrentWeather(city: CityUI) {
        showLoading(true)
        viewModelScope.launch {
            weatherRepository.getCurrentWeather(city.coord.toDataModel(), Units.METRIC)
                .map { it.toUIModel() }
                .onSuccess {
                    _currentWeather.postValue(it)
                }.onFailure {
                    Log.e(
                        TAG,
                        "loadCurrentWeather: Failed to fetch current weather for ${city.name}",
                        it
                    )
                }
            showLoading(false)
        }
    }

    fun getTempTileUrl(x: Int, y: Int, zoom: Int): String =
        weatherRepository.getTempTileUrl(x, y, zoom)

    companion object {
        const val TAG = "CityWeatherViewModel"
    }
}