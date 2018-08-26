package es.iessaladillo.pedrojoya.pr080.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr080.base.RequestState
import es.iessaladillo.pedrojoya.pr080.data.remote.EchoLiveData
import es.iessaladillo.pedrojoya.pr080.data.remote.SearchLiveData

class MainActivityViewModel: ViewModel() {

    private val _searchLiveData: SearchLiveData = SearchLiveData()
    private val _echoLiveData: EchoLiveData = EchoLiveData()

    val searchLiveData = _searchLiveData as LiveData<RequestState>
    val echoLiveData = _echoLiveData as LiveData<RequestState>

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