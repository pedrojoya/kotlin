@file:JvmName("SnackbarExt")

package pedrojoya.iessaladillo.es.pr228.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT, actionText: String = "",
             action: (View) -> Unit = {}) =
        Snackbar.make(this, message, duration).apply {
            setAction(actionText) { v -> action(v) }
        }.show()

