@file:JvmName("SnackbarExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(@StringRes messageResId: Int, actionText: CharSequence = "",
             action: (View) -> Unit = {}) =
        Snackbar.make(this, messageResId, Snackbar.LENGTH_SHORT).apply {
            setAction(actionText) { v -> action(v) }
            show()
        }
