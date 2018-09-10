@file:JvmName("SnackbarExt")

package pedrojoya.iessaladillo.es.pr226.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun snackbar(view: View, message: String, actionText: String = "",
             action: (View) -> Unit = {}) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
            setAction(actionText) { v -> action(v) }
        }.show()

