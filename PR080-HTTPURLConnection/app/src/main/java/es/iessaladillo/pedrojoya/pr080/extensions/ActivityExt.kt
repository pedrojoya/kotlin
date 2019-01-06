@file:JvmName("ActivityExt")
package es.iessaladillo.pedrojoya.pr080.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService<InputMethodManager>()
    if (inputMethodManager != null && currentFocus != null) {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}