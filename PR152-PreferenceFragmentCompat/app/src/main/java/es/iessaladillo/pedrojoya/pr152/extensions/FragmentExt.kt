package es.iessaladillo.pedrojoya.pr152.extensions

import android.content.res.Resources
import android.support.annotation.BoolRes
import android.support.annotation.NonNull
import android.support.v4.app.Fragment

@NonNull
@Throws(Resources.NotFoundException::class)
fun Fragment.getBoolean(@BoolRes id: Int): Boolean = resources.getBoolean(id)
