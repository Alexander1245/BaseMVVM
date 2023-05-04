package com.dart69.mvvm.recyclerview.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : Any, VH : BaseListAdapter.BaseViewHolder<T, *>>(
    diffCallback: BaseItemCallback<T>,
) : ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(currentList[position])

    abstract class BaseViewHolder<T : Any, VB : ViewBinding>(
        binding: VB
    ) : ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }
}