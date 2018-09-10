@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasPermission(permissionName: String) = ContextCompat
        .checkSelfPermission(applicationContext, permissionName) ==
        PackageManager.PERMISSION_GRANTED

