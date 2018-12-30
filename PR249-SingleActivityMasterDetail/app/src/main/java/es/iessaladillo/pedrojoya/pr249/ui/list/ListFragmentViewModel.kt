package es.iessaladillo.pedrojoya.pr249.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr249.data.Repository

class ListFragmentViewModel(repository: Repository) : ViewModel() {

    val students: LiveData<List<String>> = repository.queryStudents()

}
