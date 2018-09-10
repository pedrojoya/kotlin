package es.iessaladillo.pedrojoya.pr169.base

class Event<T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? =
            if (hasBeenHandled) null
            else {
                hasBeenHandled = true
                content
            }


    @Suppress("unused")
    fun peekContent(): T = content

}
