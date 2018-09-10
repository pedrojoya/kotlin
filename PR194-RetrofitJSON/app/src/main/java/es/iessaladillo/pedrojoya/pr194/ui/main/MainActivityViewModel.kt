package es.iessaladillo.pedrojoya.pr194.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr194.base.RequestState
import es.iessaladillo.pedrojoya.pr194.data.remote.Api
import es.iessaladillo.pedrojoya.pr194.data.remote.StudentsLiveData

@SuppressLint("StaticFieldLeak")
class MainActivityViewModel(api: Api) : ViewModel() {

    private val _studentsLiveData: StudentsLiveData = StudentsLiveData(api)

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
