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

fun TextView.onImeAction(imeAction: Int = EditorInfo.IME_ACTION_DONE, action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == imeAction) {
            action()
            true
        } else {
            false
        }
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

// Debe ser una extension function de TextView para que se pueda usar sobre
// esos objetos.
// El código que recibe debe ser una lambda with receiver que tenga como receptor
// un objeto de la clase que creamos para configurar los listener, para que podamos
// ejecutar sobre el receptor el código de cofiguración recibido.
fun TextView.addTextChangeListener(init: TextWatcherHelper.() -> Unit) {
    // Se crea el listener de la nueva clase.
    val listener = TextWatcherHelper()
    // Se configura con el código que se le suministra a la función.
    listener.init()
    // Se establecer como listener cuando cambie el texto del TextView.
    addTextChangedListener(listener)
}

// Se crea una clase que implementa la interfaz original y le proporcionamos una serie
// de métodos para proporcionar las lambdas. Los métodos de la interfaz invocan
// las lambdas correspondientes.
class TextWatcherHelper : TextWatcher {

    // Para configurar el afterTextChanged

    private var afterTextChangeLambda: ((editable: Editable?) -> Unit)? = null

    fun afterTextChanged(lambda: (editable: Editable?) -> Unit) {
        afterTextChangeLambda = lambda
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChangeLambda?.invoke(s)
    }

    // Para configurar el beforeTextChanged

    private var beforeTextChangedLambda: ((s: CharSequence?, start: Int, count: Int, after:
    Int) -> Unit)? = null

    fun beforeTextChanged(lambda: (s: CharSequence?, start: Int, count: Int, after: Int) ->
    Unit) {
        beforeTextChangedLambda = lambda
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChangedLambda?.invoke(s, start, count, after)
    }

    // Para configurar el onTextChanged

    private var onTextChangedLambda: ((s: CharSequence?, start: Int, before: Int, count: Int)
    -> Unit)? = null

    fun onTextChanged(lambda: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
        onTextChangedLambda = lambda
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChangedLambda?.invoke(s, start, before, count)
    }

}

