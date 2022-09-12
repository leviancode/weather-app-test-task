package com.leviancode.android.infotextest.data.entities.location

data class CityData(
    val id: Long = 0L,
    val coord: CoordinatesData = CoordinatesData(),
    val country: String = "",
    val name: String = "",
    val state: String = ""
)