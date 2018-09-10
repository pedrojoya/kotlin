@file:JvmName("SnackbarExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

fun View.snackbar(message: String, length: Int = Snackbar.LENGTH_SHORT, actionText: String = "", action: (View) -> Unit = {}) {
    Snackbar.make(this, message, length)
            .setAction(actionText) { v -> action(v) }
            .show()
}

fun View.snackbar(@StringRes messageResId: Int, length: Int = Snackbar.LENGTH_SHORT, actionText: String = "", action: (View) -> Unit = {}) {
    Snackbar.make(this, messageResId, length)
            .setAction(actionText) { v -> action(v) }
            .show()
}
