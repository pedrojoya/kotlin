package es.iessaladillo.pedrojoya.pr002.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.support.annotation.StringRes
import android.widget.Toast


fun View.hideKeyboard() {
    (this.context.getSystemService(Context
            .INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun View.toast(@StringRes stringResId: Int) {
    Toast.makeText(this.context, stringResId, Toast.LENGTH_SHORT).show()
}