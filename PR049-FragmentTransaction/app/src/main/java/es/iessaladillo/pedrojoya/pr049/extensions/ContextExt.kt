@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr049.extensions

import android.content.Context
import android.content.res.Configuration

val Context.orientation : Int
    get() = resources.configuration.orientation

fun Context.inLandscape() : Boolean = orientation == Configuration.ORIENTATION_LANDSCAPE