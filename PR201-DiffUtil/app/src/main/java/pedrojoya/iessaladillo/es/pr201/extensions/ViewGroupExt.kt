@file:JvmName("ViewGroupExt")

package pedrojoya.iessaladillo.es.pr201.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@Suppress("UNUSED_PARAMETER")
@JvmOverloads
fun ViewGroup.inflate(layoutRes: Int,
                      parent: ViewGroup = this,
                      attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)
