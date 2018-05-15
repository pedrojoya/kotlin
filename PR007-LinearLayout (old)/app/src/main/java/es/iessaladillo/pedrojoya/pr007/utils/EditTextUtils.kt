package es.iessaladillo.pedrojoya.pr007.utils

import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.setLabelView(textView: TextView, onFocusChange: (view: View, hasFocus: Boolean) -> Unit = { _, _ -> } ) {
    this.afterTextChanged {
        textView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
    }
    this.setOnFocusChangeListener { view, hasFocus ->
        textView.typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        onFocusChange.invoke(view, hasFocus)
    }
}