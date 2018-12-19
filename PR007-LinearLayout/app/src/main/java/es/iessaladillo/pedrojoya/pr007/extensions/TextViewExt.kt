@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr007.extensions

import android.view.inputmethod.EditorInfo
import android.widget.TextView

inline fun TextView.doOnImeAction(imeActionId: Int = EditorInfo.IME_ACTION_DONE, crossinline
action:
() -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeActionId) {
            action()
            true
        } else false
    }
}
