package com.leviancode.android.infotextest.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.leviancode.android.infotextest.R

@BindingAdapter(value = ["loadCityImage"])
fun ImageView.loadCityImage(oddHolder: Boolean) {
    kotlin.runCatching {
        val url = if (oddHolder) {
            IMG_FOR_ODD
        } else {
            IMG_FOR_EVEN
        }
        load(url) {
            crossfade(true)
            error(R.drawable.ic_baseline_image_not_supported_24)
            placeholder(R.drawable.ic_outline_image_24)
        }
    }.onFailure {
        load(R.drawable.ic_baseline_image_not_supported_24)
        Log.e("IMG_LIST", "Failed to load image", it)
    }
}

@BindingAdapter(value = ["loadImage"])
fun ImageView.loadImage(uri: String?) {
    if (uri.isNullOrBlank()) return

    kotlin.runCatching {
        load(uri) {
            crossfade(true)
        }
    }
}