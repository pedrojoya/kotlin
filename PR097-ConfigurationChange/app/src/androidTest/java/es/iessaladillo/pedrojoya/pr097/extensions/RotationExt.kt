@file:JvmName("RotationExt")

package es.iessaladillo.pedrojoya.pr097.extensions

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import es.iessaladillo.pedrojoya.pr097.App

fun Activity.rotateScreen() {
    val orientation = ApplicationProvider.getApplicationContext<App>()
            .resources
            .configuration.orientation
    requestedOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT)
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    else
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}
