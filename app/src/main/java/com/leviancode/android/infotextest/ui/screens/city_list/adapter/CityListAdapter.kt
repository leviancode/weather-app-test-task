package com.leviancode.android.infotextest.ui.screens.city_list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.leviancode.android.infotextest.ui.entities.location.CityUI

class CityListAdapter(
    private val onItemClick: (CityUI) -> Unit
) : PagingDataAdapter<CityUI, CityListViewHolder>(CityDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        return CityListViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, position)
    }

    private class CityDiffUtil: DiffUtil.ItemCallback<CityUI>() {
        override fun areItemsTheSame(oldItem: CityUI, newItem: CityUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityUI, newItem: CityUI): Boolean {
            return oldItem == newItem
        }
    }
}