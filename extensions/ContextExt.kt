@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.content.res.Resources
import android.support.annotation.NonNull
import android.support.annotation.PluralsRes
import android.support.annotation.ArrayRes
import android.support.annotation.BoolRes
import android.support.annotation.IntegerRes



fun Context.hasPermission(permissionName: String) = ContextCompat
        .checkSelfPermission(this.applicationContext, permissionName) ==
        PackageManager.PERMISSION_GRANTED

val Context.orientation : Int
    get() = resources.configuration.orientation

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getQuantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String =
        resources.getQuantityString(id, quantity, *formatArgs)

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getQuantityString(@PluralsRes id: Int, quantity: Int): String =
        resources.getQuantityString(id, quantity)

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getBoolean(@BoolRes id: Int): Boolean = resources.getBoolean(id)

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getInteger(@IntegerRes id: Int): Int = resources.getInteger(id)

@ColorInt
fun Context.getThemedColor(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)