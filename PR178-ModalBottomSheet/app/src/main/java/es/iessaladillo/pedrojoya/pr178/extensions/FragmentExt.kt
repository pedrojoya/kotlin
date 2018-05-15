@file:JvmName("FragmentExt")
package es.iessaladillo.pedrojoya.pr178.extensions

import android.support.v4.app.Fragment
import android.widget.Toast
import androidx.core.widget.toast

fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast =
        requireContext().toast(text, duration)
