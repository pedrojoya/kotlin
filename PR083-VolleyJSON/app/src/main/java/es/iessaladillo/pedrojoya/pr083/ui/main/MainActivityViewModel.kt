package es.iessaladillo.pedrojoya.pr083.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import es.iessaladillo.pedrojoya.pr083.base.RequestState
import es.iessaladillo.pedrojoya.pr083.data.remote.StudentsLiveData

@SuppressLint("StaticFieldLeak")
class MainActivityViewModel(requestQueue: RequestQueue, urlString: String) : ViewModel() {

    private val _studentsLiveData: StudentsLiveData = StudentsLiveData(requestQueue, urlString)

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
