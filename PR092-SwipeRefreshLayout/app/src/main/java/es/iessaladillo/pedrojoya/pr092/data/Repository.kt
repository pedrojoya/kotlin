package es.iessaladillo.pedrojoya.pr092.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr092.base.RequestState

interface Repository {

    fun queryLogs(): LiveData<List<String>>

    fun refreshLogs(): LiveData<RequestState>

    fun cancelRefresh()

}
