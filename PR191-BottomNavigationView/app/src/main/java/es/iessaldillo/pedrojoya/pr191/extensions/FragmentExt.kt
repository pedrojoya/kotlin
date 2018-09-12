@file:JvmName("FragmentExt")
package es.iessaldillo.pedrojoya.pr191.extensions

import androidx.fragment.app.Fragment

fun Fragment.extraString(extraId: String, default: String = "") =
        lazy { arguments?.getString(extraId) ?: default }

