package pedrojoya.iessaladillo.es.pr229.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pedrojoya.iessaladillo.es.pr229.data.Repository
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student

class MainActivityViewModel(private val repository: Repository) :
        ViewModel(), Repository by repository {

    var order = 1
        private set

    val students: LiveData<List<Student>> = repository.queryStudents()

    fun toggleOrder() {
        order = -order
    }

}
