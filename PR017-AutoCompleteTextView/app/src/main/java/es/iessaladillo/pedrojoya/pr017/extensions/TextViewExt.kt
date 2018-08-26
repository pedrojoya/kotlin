@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr017.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.afterTextChanged(action: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            action(editable)
        }
    })
}

fun TextView.isNotBlank() = text.isNotBlank()
