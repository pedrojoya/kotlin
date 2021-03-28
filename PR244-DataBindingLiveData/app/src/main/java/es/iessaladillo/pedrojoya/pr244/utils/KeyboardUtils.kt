package es.iessaladillo.pedrojoya.pr244.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideSoftKeyboard(): Boolean {
    val imm = context.getSystemService(
            Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return imm?.hideSoftInputFromWindow(windowToken, 0) ?: false
}