package es.iessaladillo.pedrojoya.pr080.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource
import es.iessaladillo.pedrojoya.pr080.data.Repository

internal class MainFragmentViewModel(repository: Repository) : ViewModel() {

    private val searchTextLiveData = MutableLiveData<String>()
    val searchResultLiveData: LiveData<Resource<Event<String>>>
    private val echoTextLiveData = MutableLiveData<String>()
    val echoResultLiveData: LiveData<Resource<Event<String>>>

    private var previousSearch: Call<Resource<Event<String>>>? = null
    private var previousEchoRequest: Call<Resource<Event<String>>>? = null

    init {
        searchResultLiveData = searchTextLiveData.switchMap { searchText ->
            cancelPreviousSearchTask()
            repository.search(searchText).also { previousSearch = it }
        }
        echoResultLiveData = echoTextLiveData.switchMap { echoText ->
            cancelPreviousEchoTask()
            repository.requestEcho(echoText).also { previousEchoRequest = it }
        }
    }

    fun search(searchText: String) {
        searchTextLiveData.value = searchText
    }

    fun requestEcho(text: String) {
        echoTextLiveData.value = text
    }

    private fun cancelPreviousSearchTask() {
        previousSearch?.cancel(true)
    }

    private fun cancelPreviousEchoTask() {
        previousEchoRequest?.cancel(true)
    }

    override fun onCleared() {
        super.onCleared()
        cancelPreviousSearchTask()
        cancelPreviousEchoTask()
    }

}
