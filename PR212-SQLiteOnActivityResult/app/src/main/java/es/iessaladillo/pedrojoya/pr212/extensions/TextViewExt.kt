@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr212.extensions

import android.view.inputmethod.EditorInfo
import android.widget.TextView

fun TextView.onActionDone(action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action()
            true
        } else false
    }
}

