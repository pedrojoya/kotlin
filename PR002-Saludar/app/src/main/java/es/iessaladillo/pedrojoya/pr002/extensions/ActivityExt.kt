@file:JvmName("ActivityExt")

package es.iessaladillo.pedrojoya.pr002.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun Activity.hideKeyboard() {
    getSystemService<InputMethodManager>()
            ?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
}