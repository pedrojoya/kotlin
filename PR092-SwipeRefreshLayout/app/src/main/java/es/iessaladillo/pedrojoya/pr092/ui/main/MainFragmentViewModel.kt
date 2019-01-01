package es.iessaladillo.pedrojoya.pr092.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.pr092.base.RequestState
import es.iessaladillo.pedrojoya.pr092.data.Repository

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    val data: LiveData<List<String>> = repository.queryLogs()
    private val refreshing = MutableLiveData<Boolean>()
    val refreshState: LiveData<RequestState> = refreshing.switchMap { repository.refreshLogs() }

    fun refresh() {
        refreshing.value = true
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelRefresh()
    }

}
