@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr010.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView

fun TextView.afterTextChanged(afterTextChanged: (Editable?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged(editable)
        }
    })
}

fun TextView.onAction(imeActionId: Int = EditorInfo.IME_ACTION_DONE, action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeActionId) {
            action()
            true
        } else {
            false
        }
    }
}

fun TextView.isNotBlank() = text.isNotBlank()
