@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr119.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat


fun Context.hasPermission(permissionName: String) = ContextCompat
        .checkSelfPermission(this.applicationContext, permissionName) ==
        PackageManager.PERMISSION_GRANTED

val Context.orientation : Int
    get() = resources.configuration.orientation

