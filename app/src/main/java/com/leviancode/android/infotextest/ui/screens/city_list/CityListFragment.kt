package com.leviancode.android.infotextest.ui.screens.city_list

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.leviancode.android.infotextest.R
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import com.leviancode.android.infotextest.databinding.FragmentCityListBinding
import com.leviancode.android.infotextest.ui.base.BaseFragment
import com.leviancode.android.infotextest.ui.screens.city_list.adapter.CityListAdapter
import com.leviancode.android.infotextest.utils.extensions.navigate
import com.leviancode.android.infotextest.utils.extensions.onQueryChange
import com.leviancode.android.infotextest.utils.hideKeyboard
import com.leviancode.android.infotextest.utils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityListFragment : BaseFragment<FragmentCityListBinding>(R.layout.fragment_city_list) {
    private val viewModel by viewModels<CityListViewModel>()
    private val listAdapter = CityListAdapter(::openCityWeather)

    override fun onCreated(binding: FragmentCityListBinding, savedInstanceState: Bundle?) {
        setupRecyclerView(binding)
        observeData()
        observeEvents()
        setRefreshLayoutAccordingToLoadState()
        loadAllCities()
    }

    override fun onResume() {
        super.onResume()
        setupSearchView()
    }

    private fun setupSearchView() {
        Log.i(TAG, "searchModeOn: ${viewModel.searchModeOn}")
        if (viewModel.searchModeOn){
            searchModeOn()
        } else {
            searchModeOff()
        }
    }

    private fun observeEvents() {
        binding.searchView.setOnCloseListener {
            searchModeOff()
            true
        }

        binding.fabSearch.setOnClickListener {
            searchModeOn()
        }
        binding.searchView.onQueryChange { query ->
            viewModel.findCity(query)
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.menu_refresh -> loadAllCities()
            }
            true
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadAllCities()
        }
    }

    private fun searchModeOn(){
        viewModel.searchModeOn = true
        binding.fabSearch.hide()
        binding.searchView.apply {
            isVisible = true
            requestFocus()
            isIconified = false
        }
        showKeyboard()
    }

    private fun searchModeOff(){
        viewModel.searchModeOn = false
        binding.searchView.isVisible = false
        binding.fabSearch.show()
        hideKeyboard()
    }

    private fun loadAllCities() {
        viewModel.loadAllCities()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.cities.collectLatest {
                listAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView(binding: FragmentCityListBinding) {
        binding.cityList.adapter = listAdapter
    }

    private fun openCityWeather(city: CityUI) {
        hideKeyboard()
        navigate {
            CityListFragmentDirections.actionFirstFragmentToSecondFragment(city)
        }
    }

    private fun setRefreshLayoutAccordingToLoadState() {
        lifecycleScope.launch {
            listAdapter.loadStateFlow.collectLatest {
                if (it.append is LoadState.Loading) {
                    if (viewModel.firstLoading) {
                        binding.swipeRefreshLayout.isRefreshing = true
                        viewModel.firstLoading = false
                    } else {
                        delay(1000)
                        listAdapter.loadStateFlow.lastOrNull()?.let {
                            binding.swipeRefreshLayout.isRefreshing = it.append is LoadState.Loading
                        }
                    }
                } else {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                binding.notFoundInfo.isVisible = it.refresh is LoadState.Error
                        || (binding.notFoundInfo.isVisible && it.refresh is LoadState.Loading)
            }
        }
    }

    companion object {
        const val TAG = "CityListFragment"
    }
}