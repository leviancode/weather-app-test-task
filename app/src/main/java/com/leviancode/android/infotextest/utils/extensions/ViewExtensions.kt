package com.leviancode.android.infotextest.utils.extensions

import android.widget.SearchView

fun SearchView.onQueryChange(action: (String?) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            action(newText)
            return true
        }
    })
}
