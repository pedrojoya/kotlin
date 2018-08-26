package es.iessaladillo.pedrojoya.pr080.base

sealed class RequestState {
    data class Error(val exception: Event<Exception>) : RequestState()
    data class Loading(val isLoading: Boolean) : RequestState()
    data class Result<T>(val data: T) : RequestState()
}

