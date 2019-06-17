@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr005.extensions

import android.content.Intent

fun Intent?.requireIntExtra(key: String) =
    if (this == null || !hasExtra(key)) throw IllegalArgumentException()
    else getIntExtra(key, 0)
