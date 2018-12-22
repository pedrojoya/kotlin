@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr211.extensions

import android.view.inputmethod.EditorInfo
import android.widget.TextView

fun TextView.doOnImeAction(imeActionId: Int = EditorInfo.IME_ACTION_DONE, action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeActionId) {
            action()
            true
        } else false
    }
}

