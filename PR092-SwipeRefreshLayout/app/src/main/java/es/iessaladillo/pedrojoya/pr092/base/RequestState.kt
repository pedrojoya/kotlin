package es.iessaladillo.pedrojoya.pr092.base

sealed class RequestState {
    data class Error(val exception: Event<Exception>) : RequestState()
    object Loading : RequestState()
    data class Result<T>(val data: T) : RequestState()
}

