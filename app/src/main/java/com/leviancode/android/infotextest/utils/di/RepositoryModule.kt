package com.leviancode.android.infotextest.utils.di

import com.leviancode.android.infotextest.data.repositories.CitiesRepository
import com.leviancode.android.infotextest.data.repositories.WeatherRepository
import com.leviancode.android.infotextest.data.repositories.impl.CitiesRepositoryImpl
import com.leviancode.android.infotextest.data.repositories.impl.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCitiesRepository(
        citiesRepositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    @Binds
    abstract fun bindWhetherRepository(
        citiesRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}