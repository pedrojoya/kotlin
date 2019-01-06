package es.iessaladillo.pedrojoya.pr237.base

class Resource<T> private constructor(val status: Status, val data: T?, val progress: Int?, val
exception: Event<Exception>?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    val isLoading: Boolean
        get() = status == Status.LOADING

    fun hasError(): Boolean {
        return status == Status.ERROR
    }

    fun hasSuccess(): Boolean {
        return status == Status.SUCCESS
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(exception: Exception): Resource<T> {
            return Resource(Status.ERROR, null, null, Event(exception))
        }

        fun <T> loading(progress: Int?): Resource<T> {
            return Resource(Status.LOADING, null, progress, null)
        }

    }

}
