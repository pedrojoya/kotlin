package pedrojoya.iessaladillo.es.pr229.ui.main

import androidx.lifecycle.*
import pedrojoya.iessaladillo.es.pr229.data.Repository
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student

class MainActivityViewModel(private val repository: Repository) :
        ViewModel(), Repository by repository {

    private val descLiveData = MutableLiveData<Boolean>()
    private var studentsLiveData: LiveData<List<Student>>
    private var emptyListLiveData: LiveData<Boolean>

    init {
        studentsLiveData = descLiveData.switchMap { desc -> repository.queryStudentsOrderedByName(desc) }
        emptyListLiveData = studentsLiveData.map { students -> students.isEmpty() }
        descLiveData.postValue(false)
    }

    fun toggleOrder() {
        descLiveData.postValue(!(descLiveData.value?:true))
    }

    fun isListEmpty(): LiveData<Boolean> = emptyListLiveData

    fun isInDescendentOrder(): Boolean =  descLiveData.value?:false

    fun getStudents(): LiveData<List<Student>> = studentsLiveData

}
