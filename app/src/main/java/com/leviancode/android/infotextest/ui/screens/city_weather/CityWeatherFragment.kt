package com.leviancode.android.infotextest.ui.screens.city_weather

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileProvider
import com.google.android.gms.maps.model.UrlTileProvider
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.addTileOverlay
import com.leviancode.android.infotextest.R
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import com.leviancode.android.infotextest.databinding.FragmentCityWeatherBinding
import com.leviancode.android.infotextest.ui.base.BaseFragment
import com.leviancode.android.infotextest.utils.extensions.navigateBack
import dagger.hilt.android.AndroidEntryPoint
import java.net.URL


@AndroidEntryPoint
class CityWeatherFragment :
    BaseFragment<FragmentCityWeatherBinding>(R.layout.fragment_city_weather),
    OnMapReadyCallback {
    private val args by navArgs<CityWeatherFragmentArgs>()
    private val viewModel by viewModels<CityWeatherViewModel>()

    override fun onCreated(binding: FragmentCityWeatherBinding, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        setupMapFragment()
        setTitle(args.city.name)
        loadWeather(args.city)
        observeEvents()
    }

    private fun setTitle(name: String) {
        binding.toolbar.title = name
    }

    private fun observeEvents() {
        binding.toolbar.setNavigationOnClickListener { navigateBack() }
        binding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.menu_refresh -> loadWeather(args.city)
            }
            true
        }
    }

    private fun setupMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun loadWeather(city: CityUI) {
        viewModel.loadCurrentWeather(city)
    }

    override fun onMapReady(map: GoogleMap) {
        val latLng = LatLng(
            args.city.coord.lat,
            args.city.coord.lon
        )
        map.addMarker {
            position(latLng)
            title(args.city.name)
        }
        map.addTileOverlay {
            tileProvider(createTileProvider(binding.map.width, binding.map.height))
        }
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, ZOOM)
        )
    }

    private fun createTileProvider(width: Int, height: Int): TileProvider {
        return object : UrlTileProvider(width, height) {

            override fun getTileUrl(x: Int, y: Int, zoom: Int): URL {
                return URL(viewModel.getTempTileUrl(x, y, zoom))
            }
        }
    }

    companion object{
        const val ZOOM = 10f
        const val TAG = "CityWeatherFragment"
    }
}