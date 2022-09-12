package com.leviancode.android.infotextest.utils.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackbar(textResId: Int) {
    Snackbar.make(requireView(), requireContext().getString(textResId), Snackbar.LENGTH_LONG)
        .show()
}

inline fun Fragment.navigate(direction: () -> NavDirections) {
    kotlin.runCatching {
        findNavController().navigate(direction())
    }
}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}