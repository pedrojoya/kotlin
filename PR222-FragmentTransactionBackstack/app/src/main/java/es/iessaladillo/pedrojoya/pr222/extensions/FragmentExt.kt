@file:JvmName("FragmentExt")

package es.iessaladillo.pedrojoya.pr222.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.extraString(extraId: String, @StringRes defaultStringResId: Int) =
        lazy { arguments?.getString(extraId) ?: getString(defaultStringResId) }
