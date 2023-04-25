package com.dart69.mvvm.events

import com.dart69.mvvm.strings.StringResource

data class Button(
    val text: StringResource,
    val onClick: () -> Unit = {},
)