@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr212.extensions

import android.view.inputmethod.EditorInfo
import android.widget.TextView

fun TextView.onAction(imeActionId: Int = EditorInfo.IME_ACTION_DONE, action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeActionId) {
            action()
            true
        } else false
    }
}
