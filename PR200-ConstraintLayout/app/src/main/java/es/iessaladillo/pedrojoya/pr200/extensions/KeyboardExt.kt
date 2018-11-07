@file:JvmName("KeyboardExt")

package es.iessaladillo.pedrojoya.pr200.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun Activity.hideSoftKeyboard() {
    val imm = getSystemService<InputMethodManager>()
    val view = currentFocus
    view?.let { imm?.hideSoftInputFromWindow(it.windowToken, 0) }
}
