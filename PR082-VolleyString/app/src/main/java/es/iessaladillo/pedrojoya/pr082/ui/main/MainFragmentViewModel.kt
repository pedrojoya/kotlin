package es.iessaladillo.pedrojoya.pr082.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import es.iessaladillo.pedrojoya.pr082.base.RequestState
import es.iessaladillo.pedrojoya.pr082.data.remote.echo.EchoLiveData
import es.iessaladillo.pedrojoya.pr082.data.remote.search.SearchLiveData

class MainFragmentViewModel(requestQueue: RequestQueue) : ViewModel() {

    private val _searchLiveData: SearchLiveData = SearchLiveData(requestQueue)
    private val _echoLiveData: EchoLiveData = EchoLiveData(requestQueue)

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