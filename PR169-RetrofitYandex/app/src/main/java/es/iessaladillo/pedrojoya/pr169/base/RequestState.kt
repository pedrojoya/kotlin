package es.iessaladillo.pedrojoya.pr169.base

sealed class RequestState {

    class Error(val exception: Event<Exception>) : RequestState()

    class Loading(val isLoading: Boolean) : RequestState()

    class Result<T>(val data: T) : RequestState()

}

