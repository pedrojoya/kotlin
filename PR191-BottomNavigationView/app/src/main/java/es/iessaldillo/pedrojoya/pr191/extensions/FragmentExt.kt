@file:JvmName("FragmentExt")
package es.iessaldillo.pedrojoya.pr191.extensions

import androidx.fragment.app.Fragment

fun Fragment.extraString(extraId: String) =
        lazy { arguments?.getString(extraId) ?: throw IllegalArgumentException("Must provide argument") }

fun Fragment.extraInt(extraId: String) =
        lazy { arguments?.getInt(extraId) ?: throw IllegalArgumentException("Must provide argument") }

