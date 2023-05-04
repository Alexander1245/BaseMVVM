package com.dart69.basemvvm.main

import com.dart69.basemvvm.databinding.ItemPetBinding
import com.dart69.mvvm.recyclerview.base.BaseItemCallback
import com.dart69.mvvm.recyclerview.binding.BindingAdapter

fun buildPetAdapter(
    onRootClick: (Pet) -> Unit
): BindingAdapter<Pet, ItemPetBinding> =
    BindingAdapter.Builder<Pet, ItemPetBinding>(ItemPetBinding::inflate)
        .addItemCallback(BaseItemCallback { old, new -> old.id == new.id })
        .bindViewHolder { pet, binding -> binding.textViewName.text = pet.name }
        .addClickListeners { binding -> binding.root.onClick { pet, _ -> onRootClick(pet) } }
        .build()