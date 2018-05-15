@file:JvmName("TextInputLayoutExt")
package es.iessaladillo.pedrojoya.pr149.extensions

import android.support.design.widget.TextInputLayout

fun TextInputLayout.checkValidAfterTextChange(message: String, test: (String) -> Boolean) {
    editText?.afterTextChanged {
        val input = editText?.text.toString()
        if (!input.isEmpty()) {
            if (!test(input)) {
                error = message
            } else {
                isErrorEnabled = false
            }
        } else {
            isErrorEnabled = false
        }
    }
}
