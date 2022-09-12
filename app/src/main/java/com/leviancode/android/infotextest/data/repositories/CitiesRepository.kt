package com.leviancode.android.infotextest.data.repositories

import androidx.paging.PagingData
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun fetchCities(): Flow<PagingData<CityUI>>

    fun findCity(query: String): Flow<PagingData<CityUI>>
}