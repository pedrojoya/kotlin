package es.iessaladillo.pedrojoya.pr152.extensions

import android.content.res.Resources
import androidx.annotation.BoolRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

@NonNull
@Throws(Resources.NotFoundException::class)
fun Fragment.getBoolean(@BoolRes id: Int): Boolean = resources.getBoolean(id)
