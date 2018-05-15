@file:JvmName("FragmentExt")

package es.iessaladillo.pedrojoya.pr049.extensions

import android.support.v4.app.Fragment

fun Fragment.inLandscape() : Boolean = requireContext().inLandscape()

