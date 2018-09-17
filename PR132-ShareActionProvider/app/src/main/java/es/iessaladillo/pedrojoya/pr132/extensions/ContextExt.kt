@file:JvmName("ContextExt")

package es.iessaladillo.pedrojoya.pr132.extensions
import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

@NonNull
@Throws(Resources.NotFoundException::class)
fun Fragment.getStringArray(@ArrayRes id: Int): Array<String> = requireContext().resources.getStringArray(id)