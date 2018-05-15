@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr048.extensions

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ArrayRes
import android.support.annotation.NonNull

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)