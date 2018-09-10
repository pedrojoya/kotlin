@file:JvmName("SnackbarExt")

package pedrojoya.iessaladillo.es.pr225.extensions

import android.app.Activity
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Activity.snackbar(@StringRes message: Int, actionText: String = "",
                      action: (View) -> Unit = {}) =
        Snackbar.make(currentFocus!!, message, Snackbar.LENGTH_SHORT).apply {
            setAction(actionText) { v -> action(v) }
            show()
        }
