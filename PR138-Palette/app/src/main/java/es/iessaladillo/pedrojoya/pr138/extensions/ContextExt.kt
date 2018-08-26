@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr138.extensions

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getThemedColor(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)