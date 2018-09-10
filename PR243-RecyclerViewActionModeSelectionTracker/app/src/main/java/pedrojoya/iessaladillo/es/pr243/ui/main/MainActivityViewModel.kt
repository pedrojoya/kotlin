package pedrojoya.iessaladillo.es.pr243.ui.main

import androidx.lifecycle.ViewModel
import pedrojoya.iessaladillo.es.pr243.data.Repository
import pedrojoya.iessaladillo.es.pr243.data.local.model.Student

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    private var students: List<Student>? = null
    var isInActionMode = false

    fun getStudents(forceLoad: Boolean): List<Student> {
        if (students == null || forceLoad) {
            students = repository.queryStudents()
        }
        return students!!.sortedBy { it.name }
    }

    fun addStudent(student: Student) {
        repository.addStudent(student)
    }

    fun deleteStudent(student: Student) {
        repository.deleteStudent(student)
    }

}
