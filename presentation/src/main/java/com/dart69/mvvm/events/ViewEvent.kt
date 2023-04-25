package com.dart69.mvvm.events

import android.view.View
import com.dart69.mvvm.strings.StringResource
import com.google.android.material.snackbar.Snackbar

interface ViewEvent : Event {
    fun applyOn(view: View)
}

abstract class BaseShowSnackBar(
    private val message: StringResource,
    private val duration: Int,
) : ViewEvent {
    protected open fun buildSnackBar(view: View): Snackbar =
        Snackbar.make(view, message.asString(view.context), duration)

    override fun applyOn(view: View) = buildSnackBar(view).show()
}

data class ShowCommonSnackBar(
    private val message: StringResource,
    private val duration: Int = Snackbar.LENGTH_SHORT,
) : BaseShowSnackBar(message, duration)

data class ShowSnackBarWithAction(
    private val message: StringResource,
    private val actionButton: Button,
    private val duration: Int = Snackbar.LENGTH_SHORT,
) : BaseShowSnackBar(message, duration) {
    override fun buildSnackBar(view: View): Snackbar =
        super.buildSnackBar(view)
            .setAction(actionButton.text.asString(view.context)) { actionButton.onClick() }
}