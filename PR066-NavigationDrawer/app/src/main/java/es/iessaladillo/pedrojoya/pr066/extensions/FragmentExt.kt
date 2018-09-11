@file:JvmName("FragmentExt")
package es.iessaladillo.pedrojoya.pr066.extensions

import androidx.fragment.app.Fragment

fun Fragment.extraString(extraId: String, default: String = "") =
        lazy { arguments?.getString(extraId) ?: default }

