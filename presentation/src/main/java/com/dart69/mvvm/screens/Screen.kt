package com.dart69.mvvm.screens

import androidx.viewbinding.ViewBinding
import com.dart69.mvvm.viewmodels.BaseViewModel

interface Screen<VB: ViewBinding, VM: BaseViewModel<*>> {
    val binding: VB
    val viewModel: VM
}