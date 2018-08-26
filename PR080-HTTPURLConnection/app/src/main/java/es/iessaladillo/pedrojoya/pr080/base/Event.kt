package es.iessaladillo.pedrojoya.pr080.base

open class Event<out T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    @Suppress("unused")
            /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}