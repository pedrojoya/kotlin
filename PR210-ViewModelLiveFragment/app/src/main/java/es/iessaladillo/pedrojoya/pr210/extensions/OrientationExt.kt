@file:JvmName("OrientationExt")
package es.iessaladillo.pedrojoya.pr210.extensions

import android.content.Context
import android.content.res.Configuration
import androidx.fragment.app.Fragment

fun Context.inLandScape(): Boolean =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Fragment.inLandScape(): Boolean =
        requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
