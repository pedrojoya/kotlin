package es.iessaladillo.pedrojoya.pr080.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource
import es.iessaladillo.pedrojoya.pr080.data.Repository
import es.iessaladillo.pedrojoya.pr080.data.echo.EchoRequest
import es.iessaladillo.pedrojoya.pr080.data.search.SearchRequest

internal class MainFragmentViewModel(repository: Repository) : ViewModel() {

    private val searchTextLiveData = MutableLiveData<String>()
    val searchResultLiveData: LiveData<Resource<Event<String>>>
    private val echoTextLiveData = MutableLiveData<String>()
    val echoResultLiveData: LiveData<Resource<Event<String>>>

    private var previousSearchTask: SearchRequest? = null
    private var previousEchoTask: EchoRequest? = null

    init {
        searchResultLiveData = searchTextLiveData.switchMap { searchText ->
            cancelPreviousSearchTask()
            repository.search(searchText).also { previousSearchTask = it }
        }
        echoResultLiveData = echoTextLiveData.switchMap { echoText ->
            cancelPreviousEchoTask()
            repository.requestEcho(echoText).also { previousEchoTask = it }
        }
    }

    fun search(searchText: String) {
        searchTextLiveData.value = searchText
    }

    fun requestEcho(text: String) {
        echoTextLiveData.value = text
    }

    private fun cancelPreviousSearchTask() {
        previousSearchTask?.cancel(true)
    }

    private fun cancelPreviousEchoTask() {
        previousEchoTask?.cancel(true)
    }

    override fun onCleared() {
        super.onCleared()
        cancelPreviousSearchTask()
        cancelPreviousEchoTask()
    }

}
