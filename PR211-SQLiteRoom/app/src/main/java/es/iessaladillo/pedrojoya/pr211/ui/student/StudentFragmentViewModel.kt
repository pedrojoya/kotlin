package es.iessaladillo.pedrojoya.pr211.ui.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.model.Student

class StudentFragmentViewModel(private val repository: Repository) : ViewModel() {

    private var student: LiveData<Student>? = null

    fun getStudent(studentId: Long): LiveData<Student> {
        if (student == null) {
            student = repository.getStudent(studentId)
        }
        return student!!
    }

}
