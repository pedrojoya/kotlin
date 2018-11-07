@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr017.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

inline fun TextView.setAfterTextChangedListener(crossinline action: (Editable?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            action(editable)
        }
    })
}

inline fun TextView.setOnImeAction(actionIntId: Int, crossinline action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == actionIntId) {
            action()
            true
        } else false
    }
}
