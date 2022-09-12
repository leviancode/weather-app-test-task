/*
 * Copyright (c) 2022.
 * Author: D.Levian
 * levianlink@gmail.com
 *
 */

package com.leviancode.android.infotextest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import com.leviancode.android.infotextest.databinding.ActivityMainBinding
import com.leviancode.android.infotextest.utils.managers.ConnectionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var connectionManager: ConnectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenInternetConnection()
    }

    private fun listenInternetConnection() {
        connectionManager.observe(this){  enabled ->
            binding.noInternetBar.root.isVisible = !enabled
        }
    }
}