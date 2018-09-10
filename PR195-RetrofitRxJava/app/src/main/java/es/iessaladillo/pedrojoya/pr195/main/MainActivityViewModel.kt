package es.iessaladillo.pedrojoya.pr195.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr195.data.Repository
import es.iessaladillo.pedrojoya.pr195.data.model.Student
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    private var studentsObservable: Observable<List<Student>> = loadStudents()

    fun getStudents(forceLoad: Boolean): Observable<List<Student>> {
        if (forceLoad) {
            studentsObservable = loadStudents()
        }
        return studentsObservable
    }

    private fun loadStudents() =
            repository.getStudents().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).cache()

}
