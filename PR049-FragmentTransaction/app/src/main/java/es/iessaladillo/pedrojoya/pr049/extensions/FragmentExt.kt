@file:JvmName("FragmentExt")

package es.iessaladillo.pedrojoya.pr049.extensions

import androidx.fragment.app.Fragment

fun Fragment.inLandscape() : Boolean = requireContext().inLandscape()

