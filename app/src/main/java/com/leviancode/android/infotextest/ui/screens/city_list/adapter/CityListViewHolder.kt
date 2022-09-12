package com.leviancode.android.infotextest.ui.screens.city_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import com.leviancode.android.infotextest.databinding.ListItemBinding

class CityListViewHolder(
    private val binding: ListItemBinding,
    private val onItemClick: (CityUI) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(city: CityUI, position: Int) {
        binding.model = city
        binding.oddHolder = (position + 1) % 2 != 0
        binding.root.setOnClickListener { onItemClick(city) }
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: (CityUI) -> Unit): CityListViewHolder {
            val binding =
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CityListViewHolder(binding, onItemClick)
        }
    }
}