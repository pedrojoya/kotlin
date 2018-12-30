package es.iessaladillo.pedrojoya.pr086.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr086.base.Event
import es.iessaladillo.pedrojoya.pr086.data.Repository
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    val students: LiveData<List<Student>> = repository.queryStudents()
    private val _navigateToStudentDetail = MutableLiveData<Event<Student>>()
    private val _navigateToStudentAssignments = MutableLiveData<Event<Student>>()
    private val _navigateToStudentMarks = MutableLiveData<Event<Student>>()
    private val _navigateToCallStudent = MutableLiveData<Event<Student>>()
    private val _navigateToSendMessageToStudent = MutableLiveData<Event<Student>>()
    val navigateToStudentDetail: LiveData<Event<Student>>
        get() = _navigateToStudentDetail
    val navigateToStudentAssignments: LiveData<Event<Student>>
        get() = _navigateToStudentAssignments
    val navigateToStudentMarks: LiveData<Event<Student>>
        get() = _navigateToStudentMarks
    val navigateToCallStudent: LiveData<Event<Student>>
        get() = _navigateToCallStudent
    val navigateToSendMessageToStudent: MutableLiveData<Event<Student>>
        get() = _navigateToSendMessageToStudent

    fun addStudent(student: Student) {
        repository.insertStudent(student)
    }

    fun deleteStudent(student: Student) {
        repository.deleteStudent(student)
    }

    fun showStudent(student: Student) {
        _navigateToStudentDetail.postValue(Event(student))
    }

    fun showAssignments(student: Student) {
        _navigateToStudentAssignments.postValue(Event(student))
    }

    fun showMarks(student: Student) {
        _navigateToStudentMarks.postValue(Event(student))
    }

    fun call(student: Student) {
        _navigateToCallStudent.postValue(Event(student))
    }

    fun sendMessage(student: Student) {
        _navigateToSendMessageToStudent.postValue(Event(student))
    }

}
