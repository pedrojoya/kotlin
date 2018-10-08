package es.iessaladillo.pedrojoya.pr010.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun Activity.hideSoftKeyboard() {
    val imm = getSystemService<InputMethodManager>()
    val view = currentFocus
    if (view != null) {
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
