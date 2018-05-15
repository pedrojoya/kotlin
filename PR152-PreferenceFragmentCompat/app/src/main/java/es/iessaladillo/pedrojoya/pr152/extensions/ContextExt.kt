@file:JvmName("ContextExt")
package es.iessaladillo.pedrojoya.pr152.extensions

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ArrayRes
import android.support.annotation.BoolRes
import android.support.annotation.IntegerRes
import android.support.annotation.NonNull

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getBoolean(@BoolRes id: Int): Boolean = resources.getBoolean(id)

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getInteger(@IntegerRes id: Int): Int = resources.getInteger(id)