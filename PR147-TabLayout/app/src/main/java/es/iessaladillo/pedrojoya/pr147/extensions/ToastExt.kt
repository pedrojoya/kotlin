@file:JvmName("ToastExt")
package es.iessaladillo.pedrojoya.pr147.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), messageResId, duration).show()
}
