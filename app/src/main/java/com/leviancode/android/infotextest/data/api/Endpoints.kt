package com.leviancode.android.infotextest.data.api

import com.leviancode.android.infotextest.BuildConfig

object Endpoints{
    const val BASE_URL = "https://api.openweathermap.org/data/"
    const val CURRENT_WEATHER = "2.5/weather"
    const val WEATHER_ICON = "http://openweathermap.org/img/wn/%s@4x.png"
    const val TEMP_TILE_URL = "https://tile.openweathermap.org/map/temp_new/%d/%d/%d.png?appid=${BuildConfig.OPEN_WEATHER_API_KEY}"
}