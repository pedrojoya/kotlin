package es.iessaladillo.pedrojoya.pr017.data.local.model

data class Word(val photoResId: Int, val english: String, val spanish: String) {
    override fun toString(): String = english
}