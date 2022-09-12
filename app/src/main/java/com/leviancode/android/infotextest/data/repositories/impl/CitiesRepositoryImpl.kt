package com.leviancode.android.infotextest.data.repositories.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.firebase.firestore.FirebaseFirestore
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import com.leviancode.android.infotextest.data.repositories.CitiesRepository
import com.leviancode.android.infotextest.utils.extensions.capitalizeWords
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CitiesRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CitiesRepository {
    private val citiesCollection get() = firestore.collection(CITIES_COLLECTION)

    override fun fetchCities(): Flow<PagingData<CityUI>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            FirestorePagingSource(
                citiesCollection.orderBy(FIELD_CITY_NAME)
            )
        }.flow
    }

    override fun findCity(query: String): Flow<PagingData<CityUI>> {
        val formattedQuery = query.capitalizeWords()
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            FirestorePagingSource(
                citiesCollection.orderBy(FIELD_CITY_NAME)
                    .startAt(formattedQuery)
                    .endAt(formattedQuery + "\uf8ff")
            )
        }.flow
    }

    companion object {
        private const val TAG = "CitiesRepositoryImpl"
        private const val PAGE_SIZE = 20
        private const val CITIES_COLLECTION = "cities"
        private const val FIELD_CITY_NAME = "name"
    }
}