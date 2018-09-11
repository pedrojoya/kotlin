@file:JvmName("FragmentExt")
package es.iessaladillo.pedrojoya.pr048.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.extraString(extraId: String, default: String = "") =
        lazy { arguments?.getString(extraId) ?: default }

fun Fragment.extraString(extraId: String, @StringRes defaultResId: Int) =
        lazy { arguments?.getString(extraId) ?: getString(defaultResId) }

fun Fragment.extraInt(extraId: String, default: Int = 0) =
        lazy { arguments?.getInt(extraId, default) ?: default }

fun Fragment.extraBoolean(extraId: String, default: Boolean = false) =
        lazy { arguments?.getBoolean(extraId, default) ?: default }

fun Fragment.extraCharSequenceArray(extraId: String, default: Array<CharSequence> = arrayOf()) =
        lazy { arguments?.getCharSequenceArray(extraId) ?: default }

