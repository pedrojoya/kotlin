package es.iessaladillo.pedrojoya.pr237.base

class Event<T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        if (hasBeenHandled) return null
        hasBeenHandled = true
        return content
    }

}
