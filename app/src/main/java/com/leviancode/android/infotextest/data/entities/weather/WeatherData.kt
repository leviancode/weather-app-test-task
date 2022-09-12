package com.leviancode.android.infotextest.data.entities.weather

import com.leviancode.android.infotextest.data.entities.location.CoordinatesData

data class WeatherData(
    val city: String,
    val description: String,
    val coord: CoordinatesData,
    val temp: TemperatureData,
    val humidity: Int,
    val windSpeed: Double,
    val icon: String
)
