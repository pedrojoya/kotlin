package es.iessaladillo.pedrojoya.pr211.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.local.model.Student

class ListFragmentViewModel(private val repository: Repository) : ViewModel() {

    val students: LiveData<List<Student>> = repository.queryStudents()

    fun deleteStudent(student: Student) {
        repository.deleteStudent(student)
    }

}
