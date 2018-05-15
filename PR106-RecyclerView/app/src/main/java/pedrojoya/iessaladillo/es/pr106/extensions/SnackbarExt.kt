@file:JvmName("SnackbarExt")

package pedrojoya.iessaladillo.es.pr106.extensions

import android.support.design.widget.Snackbar
import android.view.View

fun snackbar(view: View, message: String, actionText: String = "",
             action: (View) -> Unit = {}) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
            setAction(actionText) { v -> action(v) }
            show()
        }

