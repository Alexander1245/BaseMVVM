package com.dart69.mvvm.recyclerview.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

@SuppressLint("DiffUtilEquals")
abstract class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    companion object {
        operator fun <T : Any> invoke(
            areTheSame: (T, T) -> Boolean = { oldItem, newItem -> oldItem === newItem },
        ): BaseItemCallback<T> = object : BaseItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
                areTheSame(oldItem, newItem)
        }
    }
}