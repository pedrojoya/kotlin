@file:JvmName("SnackbarExt")

package es.iessaldillo.pedrojoya.pr191.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

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
