@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr057.extensions
import android.content.Context
import android.content.res.Resources
import android.support.annotation.NonNull
import android.support.annotation.PluralsRes

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getQuantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String =
        resources.getQuantityString(id, quantity, *formatArgs)
