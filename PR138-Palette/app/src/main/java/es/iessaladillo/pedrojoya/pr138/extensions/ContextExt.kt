@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr138.extensions

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat

@ColorInt
fun Context.getThemedColor(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)