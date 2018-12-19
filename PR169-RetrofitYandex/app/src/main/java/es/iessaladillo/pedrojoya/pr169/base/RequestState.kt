package es.iessaladillo.pedrojoya.pr169.base

sealed class RequestState<out T> {

    class Error(val exception: Event<Exception>) : RequestState<Nothing>()

    object Loading : RequestState<Nothing>()

    class Result<T>(val data: T) : RequestState<T>()

}
