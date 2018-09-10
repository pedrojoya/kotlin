@file:JvmName("FragmentExt")
package es.iessaladillo.pedrojoya.pr211.extensions

import androidx.fragment.app.Fragment

fun Fragment.extraLong(extraId: String, default: Long = 0) =
        lazy { arguments?.getLong(extraId, default) ?: default }
