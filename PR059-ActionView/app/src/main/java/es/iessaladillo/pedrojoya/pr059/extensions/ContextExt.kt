@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr059.extensions
import android.content.Context
import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.annotation.NonNull

@NonNull
@Throws(Resources.NotFoundException::class)
fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)