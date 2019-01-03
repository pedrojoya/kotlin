@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr119.extensions

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.hasPermission(permissionName: String) = ContextCompat
        .checkSelfPermission(requireContext(), permissionName) ==
        PackageManager.PERMISSION_GRANTED

