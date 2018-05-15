@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr011.extensions

import android.graphics.Typeface
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.View
import android.widget.TextView

fun TextView.afterTextChanged(action: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            action.invoke(editable)
        }
    })
}

fun TextView.labelTextView(
        lbl: TextView,
        afterTextChange: (Editable?) -> Unit = {},
        onFocusChange: (v: View, hasFocus: Boolean) -> Unit = { v, f -> }
    ) {
    this.afterTextChanged {
        lbl.visibility = if (TextUtils.isEmpty(this.text.toString())) View.INVISIBLE else View.VISIBLE
        afterTextChange.invoke(it)
    }
    this.setOnFocusChangeListener { v, hasFocus ->
        lbl.typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        onFocusChange.invoke(v, hasFocus)
    }
}

fun TextView.onActionDone(action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action()
            true
        }
        false
    }
}

fun TextView.checkValidAfterTextChange(message: String, test: (String) -> Boolean) {
    afterTextChanged {
        val input = text.toString()
        if (!input.isEmpty()) {
            error = if (!test(input)) message else null
        }
    }
}

fun TextView.isNotBlank() = text.isNotBlank()

fun TextView.isNotEmpty() = text.isNotEmpty()

