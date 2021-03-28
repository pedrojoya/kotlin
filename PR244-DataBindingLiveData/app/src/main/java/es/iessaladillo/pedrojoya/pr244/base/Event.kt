package es.iessaladillo.pedrojoya.pr244.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false
        private set // Allow external read but not write

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit) {
    observe(owner, Observer { it.getContentIfNotHandled()?.let(onEventUnhandledContent) })
}

open class EventViewModel<T> : androidx.lifecycle.ViewModel() {

    private val _event: MutableLiveData<Event<T>> = MutableLiveData()
    val event: LiveData<Event<T>>
        get() = _event

    fun submit(value: T) {
        _event.value = Event(value)
    }

}
