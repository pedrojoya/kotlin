package es.iessaladillo.pedrojoya.pr059.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.pr059.data.Repository

class MainFragmentViewModel(repository: Repository) : ViewModel() {

    private val searchQueryLiveData = MutableLiveData<String>()
    var searchQuery: String?
        get() = searchQueryLiveData.value
        set(value) = searchQueryLiveData.postValue(value)
    val students: LiveData<List<String>>
    var isSearchViewExpanded: Boolean = false

    init {
        students = searchQueryLiveData.switchMap { criteria -> repository.queryStudents(criteria) }
        searchQueryLiveData.postValue("")
    }

}
