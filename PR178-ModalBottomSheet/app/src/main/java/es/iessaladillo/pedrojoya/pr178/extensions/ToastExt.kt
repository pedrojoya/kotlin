@file:JvmName("ToastExt")
package es.iessaladillo.pedrojoya.pr178.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(messageResId: Int, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, messageResId, duration).show()

fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(requireContext(), text, duration).show()
