package es.iessaladillo.pedrojoya.pr249.ui.list

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr249.data.Repository

class ListFragmentViewModel(private val repository: Repository) : ViewModel() {

    private var students: List<String>? = null

    fun getStudents(forceLoad: Boolean): List<String> {
        if (students == null || forceLoad) {
            students = repository.queryStudents()
        }
        return students!!
    }

}
