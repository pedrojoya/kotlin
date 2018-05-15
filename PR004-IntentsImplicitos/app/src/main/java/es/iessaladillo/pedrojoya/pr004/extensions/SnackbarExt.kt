@file:JvmName("SnackbarExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

fun snackbar(view: View, @StringRes messageResId: Int, @StringRes actionTextResId: Int,
             action: (View) -> Unit = {}) =
        Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT).apply {
            setAction(actionTextResId) { v -> action(v) }
            show()
        }
