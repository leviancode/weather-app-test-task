package com.leviancode.android.infotextest.data.api

import com.leviancode.android.infotextest.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(Endpoints.CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): WeatherResponse
}