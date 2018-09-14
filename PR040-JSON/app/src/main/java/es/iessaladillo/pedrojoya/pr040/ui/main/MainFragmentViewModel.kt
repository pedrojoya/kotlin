package es.iessaladillo.pedrojoya.pr040.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr040.base.RequestState
import es.iessaladillo.pedrojoya.pr040.data.remote.StudentsLiveData

@SuppressLint("StaticFieldLeak")
class MainFragmentViewModel : ViewModel() {

    private val _studentsLiveData: StudentsLiveData = StudentsLiveData()

    val students: LiveData<RequestState>
        get() = _studentsLiveData

    fun forceLoadStudents() {
        _studentsLiveData.loadData()
    }

    override fun onCleared() {
        _studentsLiveData.cancel()
        super.onCleared()
    }

}
