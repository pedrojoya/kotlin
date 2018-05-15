@file:JvmName("SnackbarExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

fun snackbar(view: View, message: String, actionText: String = "",
                    action: (View) -> Unit = {}) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
                    setAction(actionText) { v -> action(v) }
                    show()
                }

fun snackbar(view: View, message: String, @StringRes actionTextResId: Int,
             action: (View) -> Unit = {}) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
            setAction(actionTextResId) { v -> action(v) }
            show()
        }

fun snackbar(view: View, @StringRes message: Int, actionText: String = "",
             action: (View) -> Unit = {}) =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
            setAction(actionText) { v -> action(v) }
            show()
        }

fun snackbar(view: View, @StringRes messageResId: Int, @StringRes actionTextResId: Int,
             action: (View) -> Unit = {}) =
        Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT).apply {
            setAction(actionTextResId) { v -> action(v) }
            show()
        }
