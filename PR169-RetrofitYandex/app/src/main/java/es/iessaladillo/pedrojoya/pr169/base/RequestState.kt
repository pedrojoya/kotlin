package es.iessaladillo.pedrojoya.pr169.base

sealed class RequestState <T> {

    class Error<T>(val exception: Event<Exception>) : RequestState<T>()

    class Loading<T>(val isLoading: Boolean) : RequestState<T>()

    class Result<T>(val data: T) : RequestState<T>()

}
