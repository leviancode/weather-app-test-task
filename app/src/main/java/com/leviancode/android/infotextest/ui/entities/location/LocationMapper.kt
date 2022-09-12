package com.leviancode.android.infotextest.ui.entities.location

import com.leviancode.android.infotextest.data.entities.location.CoordinatesData

fun CoordinatesData.toUIModel() = CoordinatesUI(
    lat = lat,
    lon = lon
)

fun CoordinatesUI.toDataModel() = CoordinatesData(
    lat = lat,
    lon = lon
)