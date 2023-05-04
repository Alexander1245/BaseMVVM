package com.dart69.mvvm.events

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

interface ActivityResultEvent<I> : Event {
    fun applyOn(launcher: ActivityResultLauncher<I>)
}

data class StartActivity(
    private val intent: String,
) : ActivityResultEvent<Intent> {
    override fun applyOn(launcher: ActivityResultLauncher<Intent>) =
        launcher.launch(Intent(intent))
}

data class RequestPermission(
    private val permission: String,
) : ActivityResultEvent<String> {
    override fun applyOn(launcher: ActivityResultLauncher<String>) =
        launcher.launch(permission)
}

class RequestMultiplePermissions(
    private val permissions: Array<String>
): ActivityResultEvent<Array<String>> {
    override fun applyOn(launcher: ActivityResultLauncher<Array<String>>) =
        launcher.launch(permissions)
}