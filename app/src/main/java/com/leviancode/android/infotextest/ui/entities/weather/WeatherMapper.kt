package com.leviancode.android.infotextest.ui.entities.weather

import com.leviancode.android.infotextest.data.entities.weather.WeatherData
import com.leviancode.android.infotextest.ui.entities.location.CoordinatesUI
import com.leviancode.android.infotextest.ui.entities.location.toUIModel

fun WeatherData.toUIModel() = WeatherUI(
    city = city,
    description = description,
    coord = coord.toUIModel(),
    temp = TemperatureUI(
        current = temp.current,
        max = temp.max,
        min = temp.min
    ),
    humidity = humidity,
    windSpeed = windSpeed,
    icon = icon,
)