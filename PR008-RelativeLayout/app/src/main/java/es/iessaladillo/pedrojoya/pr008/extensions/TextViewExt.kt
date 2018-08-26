@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr008.extensions

import android.graphics.Typeface
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView

fun TextView.afterTextChanged(action: (Editable?) -> Unit) {
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

fun TextView.labelTextView(
        lbl: TextView,
        afterTextChange: (Editable?) -> Unit = {},
        onFocusChange: (v: View, hasFocus: Boolean) -> Unit = { _, _ -> }
    ) {
    afterTextChanged {
        lbl.visibility = if (TextUtils.isEmpty(text.toString())) View.INVISIBLE else View.VISIBLE
        afterTextChange(it)
    }
    setOnFocusChangeListener { v, hasFocus ->
        lbl.typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        onFocusChange(v, hasFocus)
    }
}

fun TextView.isNotBlank() = text.isNotBlank()

