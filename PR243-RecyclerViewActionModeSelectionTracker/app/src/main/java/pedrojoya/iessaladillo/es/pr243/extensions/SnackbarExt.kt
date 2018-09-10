@file:JvmName("SnackbarExt")
package pedrojoya.iessaladillo.es.pr243.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}