@file:JvmName("TextInputLayoutExt")
package es.iessaladillo.pedrojoya.pr212.extensions

import com.google.android.material.textfield.TextInputLayout


inline fun TextInputLayout.checkValid(errorMessage: String,
                                      test: (String) -> Boolean = { s -> s.isNotBlank() }): Boolean {
    return if (!test(editText!!.text.toString())) {
        isErrorEnabled = true
        error = errorMessage
        false
    } else {
        isErrorEnabled = false
        error = ""
        true
    }
}
