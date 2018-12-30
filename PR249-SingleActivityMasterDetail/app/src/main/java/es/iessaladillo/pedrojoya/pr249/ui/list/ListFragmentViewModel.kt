package es.iessaladillo.pedrojoya.pr249.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr249.base.Event
import es.iessaladillo.pedrojoya.pr249.data.Repository

class ListFragmentViewModel(repository: Repository) : ViewModel() {

    val students: LiveData<List<String>> = repository.queryStudents()
    val navigateToDetail: MutableLiveData<Event<String>> = MutableLiveData()

    fun onItemSelected(item: String) {
        navigateToDetail.postValue(Event(item))
    }

}
