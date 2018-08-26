package es.iessaladillo.pedrojoya.pr180.base

class Event<T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? =
            if (hasBeenHandled)
                null
            else {
                hasBeenHandled = true
                content
            }

    @Suppress("unused")
    fun peekContent(): T = content

}
