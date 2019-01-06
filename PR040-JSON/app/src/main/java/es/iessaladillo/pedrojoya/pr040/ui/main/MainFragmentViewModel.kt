package es.iessaladillo.pedrojoya.pr040.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.pr040.base.Resource
import es.iessaladillo.pedrojoya.pr040.data.Repository
import es.iessaladillo.pedrojoya.pr040.data.model.Student

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {

    private val queryStudentsTrigger = MutableLiveData<Boolean>()
    val students: LiveData<Resource<List<Student>>> = queryStudentsTrigger.switchMap {
        repository.queryStudents()
    }

    init {
        refreshStudents()
    }

    fun refreshStudents() {
        queryStudentsTrigger.postValue(true)
    }

}
