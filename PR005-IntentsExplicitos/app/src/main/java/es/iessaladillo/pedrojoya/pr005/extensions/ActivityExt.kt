@file:JvmName("ActivityExt")
package es.iessaladillo.pedrojoya.pr005.extensions

import android.app.Activity

fun Activity.extraString(key: String, defaultValue: String) =
    lazy { intent?.getStringExtra(key) ?: defaultValue }

fun Activity.extraInt(key: String, defaultValue: Int) =
    lazy { intent?.getIntExtra(key, defaultValue) ?: defaultValue }
