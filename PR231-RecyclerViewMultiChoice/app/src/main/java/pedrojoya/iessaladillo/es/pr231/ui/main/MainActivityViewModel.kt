package pedrojoya.iessaladillo.es.pr231.ui.main

import androidx.lifecycle.ViewModel
import pedrojoya.iessaladillo.es.pr231.data.Repository
import pedrojoya.iessaladillo.es.pr231.data.local.model.Student

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    private var students: List<Student>? = null

    fun getStudents(forceLoad: Boolean): List<Student> {
        if (students == null || forceLoad) {
            students = repository.queryStudents()
        }
        return students!!.sortedBy { it.name }
    }

}
