package com.leviancode.android.infotextest.data.repositories

import com.leviancode.android.infotextest.data.entities.location.CoordinatesData
import com.leviancode.android.infotextest.data.entities.weather.Units
import com.leviancode.android.infotextest.data.entities.weather.WeatherData

interface WeatherRepository {

    suspend fun getCurrentWeather(coordinates: CoordinatesData, units: Units): Result<WeatherData>

    fun getTempTileUrl(x: Int, y: Int, zoom: Int): String
}