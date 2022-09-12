package com.leviancode.android.infotextest.ui.entities.weather

import com.leviancode.android.infotextest.ui.entities.location.CoordinatesUI

data class WeatherUI(
    val city: String,
    val description: String,
    val coord: CoordinatesUI,
    val temp: TemperatureUI,
    val humidity: Int,
    val windSpeed: Double,
    val icon: String
)
