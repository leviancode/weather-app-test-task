package com.leviancode.android.infotextest.utils.extensions

import java.util.Locale

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(Locale.US, this).toDouble()