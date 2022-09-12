package com.leviancode.android.infotextest.utils.extensions

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject

inline fun <reified T> QuerySnapshot?.toObjects() =
    this?.documents?.mapNotNull { it.toObject<T>() } ?: listOf<T>()