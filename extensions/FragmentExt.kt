@file:JvmName("FragmentExt")
package es.iessaladillo.pedrojoya.pr212.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import androidx.core.widget.toast

inline fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast {
    return requireContext().toast(text, duration)
}

inline fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT): Toast {
    return requireContext().toast(resId, duration)
}

fun Fragment.extraString(extraId: String, default: String = "") =
        lazy { arguments?.getString(extraId) ?: default }

