@file:JvmName("FragmentExt")

package es.iessaladillo.pedrojoya.pr222.extensions

import android.support.v4.app.Fragment

fun Fragment.inLandscape() : Boolean = requireContext().inLandscape()

