@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr212.extensions

import android.app.Activity

fun Activity.extraLong(extraId: String, default: Long = 0) =
    lazy { intent?.getLongExtra(extraId, default) ?: default }
