package com.leviancode.android.infotextest.data.repositories.impl

import android.util.Log
import com.leviancode.android.infotextest.BuildConfig
import com.leviancode.android.infotextest.data.api.Endpoints
import com.leviancode.android.infotextest.data.api.WeatherService
import com.leviancode.android.infotextest.data.entities.location.CoordinatesData
import com.leviancode.android.infotextest.data.entities.weather.Units
import com.leviancode.android.infotextest.data.entities.weather.WeatherData
import com.leviancode.android.infotextest.data.entities.weather.mapToSimpleWeather
import com.leviancode.android.infotextest.data.repositories.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun getCurrentWeather(coordinates: CoordinatesData, units: Units): Result<WeatherData> {
        return kotlin.runCatching {
            weatherService.getCurrentWeather(
                lat = coordinates.lat.toString(),
                lon = coordinates.lon.toString(),
                units = units.value,
                appId = BuildConfig.OPEN_WEATHER_API_KEY
            ).mapToSimpleWeather()
        }.onFailure {
            Log.e(TAG, "Failed to fetch current weather", it)
        }
    }

    override fun getTempTileUrl(x: Int, y: Int, zoom: Int): String {
        return String.format(Endpoints.TEMP_TILE_URL, zoom, x, y)
    }

    companion object{
        const val TAG = "WeatherRepositoryImpl"
    }
}