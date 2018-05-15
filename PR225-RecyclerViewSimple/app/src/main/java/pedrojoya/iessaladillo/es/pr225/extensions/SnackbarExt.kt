@file:JvmName("SnackbarExt")

package pedrojoya.iessaladillo.es.pr225.extensions

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

fun snackbar(view: View, @StringRes message: Int, actionText: String = "",
             action: (View) -> Unit = {}) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
            setAction(actionText) { v -> action(v) }
            show()
        }
