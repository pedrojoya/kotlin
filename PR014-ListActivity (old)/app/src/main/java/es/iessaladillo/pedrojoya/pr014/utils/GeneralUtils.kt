package es.iessaladillo.pedrojoya.pr014.utils

import android.util.Log

fun Any.e(any: Any? = "no message provided") {
    Log.e(this.javaClass.simpleName, any.toString())
}