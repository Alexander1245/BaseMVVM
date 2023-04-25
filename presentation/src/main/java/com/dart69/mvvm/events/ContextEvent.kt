package com.dart69.mvvm.events

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.dart69.mvvm.R
import com.dart69.mvvm.strings.StringResource

interface ContextEvent : Event {
    fun applyOn(context: Context)
}

data class ShowToast(
    private val message: StringResource,
    private val duration: Int = Toast.LENGTH_SHORT,
) : ContextEvent {
    override fun applyOn(context: Context) =
        Toast.makeText(context, message.asString(context), duration).show()
}

abstract class BaseShowDialog(
    private val title: StringResource,
    private val message: StringResource,
    private val positiveButton: Button,
) : ContextEvent {
    protected open fun buildDialog(context: Context): AlertDialog.Builder =
        AlertDialog.Builder(context)
            .setTitle(title.asString(context))
            .setMessage(message.asString(context))
            .setPositiveButton(positiveButton.text.asString(context)) { _, _ ->
                positiveButton.onClick()
            }

    override fun applyOn(context: Context) {
        buildDialog(context).show()
    }
}

data class ShowCommonDialog(
    private val title: StringResource,
    private val message: StringResource,
    private val positiveButton: Button = Button(StringResource.Common(R.string.ok)),
) : BaseShowDialog(title, message, positiveButton)

data class ShowDialogWithDismiss(
    private val title: StringResource,
    private val message: StringResource,
    private val positiveButton: Button = Button(StringResource.Common(R.string.ok)),
    private val negativeButton: Button = Button(StringResource.Common(R.string.no_thanks)),
) : BaseShowDialog(title, message, positiveButton) {
    override fun buildDialog(context: Context): AlertDialog.Builder =
        super.buildDialog(context)
            .setNegativeButton(negativeButton.text.asString(context)) { _, _ ->
                negativeButton.onClick()
            }
}