package es.iessaladillo.pedrojoya.pr040.base

class Event<T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) null
        else {
            hasBeenHandled = true
            content
        }


    @Suppress("unused")
            /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

}
