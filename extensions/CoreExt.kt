@file:JvmName("CoreExt")
package es.iessaladillo.pedrojoya.pr018.extensions

inline fun consume(action: () -> Unit): Boolean {
    action()
    return true
}

@Suppress("unused")
fun Unit.thenTrue(): Boolean = true