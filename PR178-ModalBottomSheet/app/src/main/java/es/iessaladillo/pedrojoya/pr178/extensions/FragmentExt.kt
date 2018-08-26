@file:JvmName("FragmentExt")
package es.iessaladillo.pedrojoya.pr178.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(requireContext(), text, duration).show()
