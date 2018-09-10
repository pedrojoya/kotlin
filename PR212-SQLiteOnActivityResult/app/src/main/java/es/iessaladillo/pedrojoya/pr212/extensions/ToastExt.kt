@file:JvmName("ToastExt")
package es.iessaladillo.pedrojoya.pr212.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), resId, duration).show()
}

