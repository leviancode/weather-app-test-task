package com.leviancode.android.infotextest.utils.extensions

fun String.capitalizeWords(): String = trim()
    .lowercase()
    .split(" ")
    .joinToString(" ") { it.replaceFirstChar { it.uppercaseChar() } }