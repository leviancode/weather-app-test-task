package com.leviancode.android.infotextest.ui.entities.location

import java.io.Serializable

data class CityUI(
    val id: Long = 0L,
    val coord: CoordinatesUI = CoordinatesUI(),
    val country: String = "",
    val name: String = "",
    val state: String = ""
) : Serializable {
    val fullName: String get() = "$name, $country"
}