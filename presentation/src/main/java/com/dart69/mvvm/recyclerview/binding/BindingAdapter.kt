package com.dart69.mvvm.recyclerview.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.dart69.mvvm.recyclerview.base.BaseItemCallback
import com.dart69.mvvm.recyclerview.base.BaseAdapter

typealias BindingProvider<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB
typealias ViewHolderBinderScope<T, VB> = ViewHolder.(T, VB) -> Unit
typealias ViewHolderListenersScope<T, VB> = BindingAdapter<T, VB>.BindingViewHolder.(VB) -> Unit
typealias ViewHolderClickListener<T> = (T, View) -> Unit

open class BindingAdapter<T : Any, VB : ViewBinding> private constructor(
    diffCallback: BaseItemCallback<T>,
    private val provider: BindingProvider<VB>,
    private val binderScope: ViewHolderBinderScope<T, VB>,
    private val listenersScope: ViewHolderListenersScope<T, VB>,
) : BaseAdapter<T, BindingAdapter<T, VB>.BindingViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
        BindingViewHolder(provider(LayoutInflater.from(parent.context), parent, ATTACH_TO_PARENT))

    open inner class BindingViewHolder(
        private val binding: VB
    ) : BaseViewHolder<T, VB>(binding) {
        fun View.onClick(block: ViewHolderClickListener<T>) = setOnClickListener { view ->
            block(currentList[bindingAdapterPosition], view)
        }

        init {
            listenersScope(binding)
        }

        override fun bind(item: T) = binderScope(item, binding)
    }

    class Builder<T : Any, VB : ViewBinding>(
        private val provider: BindingProvider<VB>,
    ) {
        private var itemCallback: BaseItemCallback<T> = BaseItemCallback.invoke()
        private var binderScope: ViewHolderBinderScope<T, VB> = { _, _ -> }
        private var listenersScope: ViewHolderListenersScope<T, VB> = { _ -> }

        fun addItemCallback(callback: BaseItemCallback<T>): Builder<T, VB> = apply {
            itemCallback = callback
        }

        fun bindViewHolder(block: ViewHolderBinderScope<T, VB>): Builder<T, VB> = apply {
            binderScope = block
        }

        fun addClickListeners(block: ViewHolderListenersScope<T, VB>): Builder<T, VB> = apply {
            listenersScope = block
        }

        fun build(): BindingAdapter<T, VB> = BindingAdapter(
            diffCallback = itemCallback,
            provider = provider,
            binderScope = binderScope,
            listenersScope = listenersScope,
        )
    }

    companion object {
        const val ATTACH_TO_PARENT = false
    }
}