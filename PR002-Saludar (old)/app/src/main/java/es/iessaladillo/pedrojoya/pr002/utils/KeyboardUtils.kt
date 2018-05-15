package es.iessaladillo.pedrojoya.pr002.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(view: View) {
    val inputMethodManager : InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
