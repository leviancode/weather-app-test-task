package com.leviancode.android.infotextest.data.entities.weather

import com.leviancode.android.infotextest.data.api.Endpoints
import com.leviancode.android.infotextest.data.api.response.WeatherResponse
import com.leviancode.android.infotextest.data.entities.location.CoordinatesData
import com.leviancode.android.infotextest.utils.extensions.round

fun WeatherResponse.mapToSimpleWeather() = WeatherData(
    city = name,
    description = if (weather.isNotEmpty()) {
        weather[0].description.replaceFirstChar { it.uppercase() }
    } else "",
    coord = CoordinatesData(coord.lat, coord.lon),
    temp = TemperatureData(
        current = main.temp.toInt(),
        max = main.tempMax.toInt(),
        min = main.tempMin.toInt()
    ),
    humidity = main.humidity,
    windSpeed = wind.speed.round(),
    icon = if (weather.isNotEmpty()) {
        String.format(Endpoints.WEATHER_ICON, weather[0].icon)
    } else "",
)