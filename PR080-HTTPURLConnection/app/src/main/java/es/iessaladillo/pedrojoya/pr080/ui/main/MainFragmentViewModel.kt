package es.iessaladillo.pedrojoya.pr080.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource
import es.iessaladillo.pedrojoya.pr080.data.Repository

internal class MainFragmentViewModel(repository: Repository) : ViewModel() {

    private val searchTextLiveData = MutableLiveData<String>()
    val searchResultLiveData: LiveData<Resource<Event<String>>>
    private val echoTextLiveData = MutableLiveData<String>()
    val echoResultLiveData: LiveData<Resource<Event<String>>>

    init {
        searchResultLiveData = searchTextLiveData.switchMap { repository.search(it) }
        echoResultLiveData = echoTextLiveData.switchMap { repository.requestEcho(it) }
    }

    fun search(searchText: String) {
        searchTextLiveData.value = searchText
    }

    fun requestEcho(text: String) {
        echoTextLiveData.value = text
    }

}
