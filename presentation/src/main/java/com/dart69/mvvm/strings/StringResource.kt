package com.dart69.mvvm.strings

import android.content.Context
import androidx.annotation.StringRes

sealed class StringResource {
    abstract fun asString(context: Context): String

    data class Formatted(
        @StringRes private val resId: Int,
        private val formatArgs: List<Any>,
    ) : StringResource() {
        constructor(resId: Int, vararg formatArgs: Any) : this(resId, formatArgs.toList())

        override fun asString(context: Context): String =
            context.getString(resId, *formatArgs.toTypedArray())
    }

    data class Common(
        @StringRes private val resId: Int,
    ) : StringResource() {
        override fun asString(context: Context): String = context.getString(resId)
    }

    object Empty : StringResource() {
        override fun asString(context: Context): String = ""
    }
}

fun Int.asStringResource(vararg formatArgs: Any): StringResource =
    if (formatArgs.isEmpty()) {
        StringResource.Common(this)
    } else {
        StringResource.Formatted(this, *formatArgs)
    }
