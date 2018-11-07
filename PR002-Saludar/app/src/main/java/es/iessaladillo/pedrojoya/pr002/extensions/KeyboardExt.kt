@file:JvmName("KeyboardExt")
package es.iessaladillo.pedrojoya.pr002.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun Activity.hideKeyboard() {
    currentFocus?.let {
        getSystemService<InputMethodManager>()
                ?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}