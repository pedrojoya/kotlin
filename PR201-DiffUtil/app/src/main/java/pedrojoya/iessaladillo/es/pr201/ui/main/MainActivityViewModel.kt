package pedrojoya.iessaladillo.es.pr201.ui.main

import android.arch.lifecycle.ViewModel
import pedrojoya.iessaladillo.es.pr201.data.Repository
import pedrojoya.iessaladillo.es.pr201.data.Student

class MainActivityViewModel(private val repository: Repository) :
        ViewModel(), Repository by repository {

    private var order = 1
    private var students: List<Student>? = null

    fun toggleOrder() {
        order = -order
    }

    fun getStudents(forceLoad: Boolean): List<Student> {
        if (students == null || forceLoad) {
            students = repository.queryStudents(order)
        }
        return students?:ArrayList()
    }

}
