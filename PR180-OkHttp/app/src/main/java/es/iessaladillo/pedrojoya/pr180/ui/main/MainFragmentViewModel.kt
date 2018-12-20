package es.iessaladillo.pedrojoya.pr180.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr180.base.RequestState
import es.iessaladillo.pedrojoya.pr180.data.remote.echo.EchoLiveData
import es.iessaladillo.pedrojoya.pr180.data.remote.search.SearchLiveData
import okhttp3.OkHttpClient

class MainFragmentViewModel(okHttpClient: OkHttpClient) : ViewModel() {

    private val _searchLiveData: SearchLiveData = SearchLiveData(okHttpClient)
    private val _echoLiveData: EchoLiveData = EchoLiveData(okHttpClient)

    val searchLiveData: LiveData<RequestState>
        get() = _searchLiveData

    val echoLiveData: EchoLiveData
        get() = _echoLiveData

    fun search(text: String) {
        _searchLiveData.search(text)
    }

    fun requestEcho(text: String) {
        _echoLiveData.requestEcho(text)
    }

    override fun onCleared() {
        _searchLiveData.cancel()
        _echoLiveData.cancel()
        super.onCleared()
    }

}
