@file:JvmName("TextViewExt")

package es.iessaladillo.pedrojoya.pr005.extensions

import android.text.Editable
import android.text.TextWatcher
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

fun TextView.isNotBlank() = text.isNotBlank()

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